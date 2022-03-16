package com.mgm.kiliaro.generals.extensions;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.mgm.kiliaro.generals.TopLevelFunctionsKt.fastBlur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.mgm.kiliaro.R;

/**
 * Created by Majid-Golmoradi on 3/16/2022.
 * Email: golmoradi.majid@gmail.com
 */
public class PhotoFullPopupWindow extends PopupWindow {

    private static PhotoFullPopupWindow instance = null;
    View view;
    Context mContext;
    PhotoView photoView;
    ProgressBar loading;
    TextView txtCreateAt;
    ViewGroup parent;


    public PhotoFullPopupWindow(Context ctx, int layout, View v, String imageUrl, Bitmap bitmap, String createAt) {
        super(((LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.popup_photo_full, null), ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        setElevation(5.0f);
        this.mContext = ctx;
        this.view = getContentView();
        ImageButton closeButton = this.view.findViewById(R.id.ib_close);
        setOutsideTouchable(true);

        setFocusable(true);
        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                dismiss();
            }
        });
        //---------Begin customising this popup--------------------

        photoView = (PhotoView) view.findViewById(R.id.image);
        loading = (ProgressBar) view.findViewById(R.id.loading);
        txtCreateAt = (TextView) view.findViewById(R.id.txtCreateAt);
        photoView.setMaximumScale(6);
        parent = (ViewGroup) photoView.getParent();
        // ImageUtils.setZoomable(imageView);
        //----------------------------
        if (bitmap != null) {
            parent.setBackground(new BitmapDrawable(mContext.getResources(), fastBlur(Bitmap.createScaledBitmap(bitmap, 50, 50, true))));// ));
            photoView.setImageBitmap(bitmap);
        }
        loading.setIndeterminate(true);
        loading.setVisibility(View.VISIBLE);
        Glide.with(ctx).asBitmap()
                .load(imageUrl)
                .error(R.drawable.ic_launcher_background)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        loading.setIndeterminate(false);
                        loading.setBackgroundColor(Color.LTGRAY);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        parent.setBackground(new BitmapDrawable(mContext.getResources(), fastBlur(Bitmap.createScaledBitmap(resource, 50, 50, true))));// ));
                        photoView.setImageBitmap(resource);

                        loading.setVisibility(View.GONE);
                        txtCreateAt.setText(createAt);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photoView);

        showAtLocation(v, Gravity.CENTER, 0, 0);

        //------------------------------

    }

}
