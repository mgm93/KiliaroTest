package com.mgm.kiliaro.generals.extensions

import android.content.Context
import com.mgm.kiliaro.generals.fastBlur
import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import com.mgm.kiliaro.R
import android.view.ViewGroup
import com.github.chrisbanes.photoview.PhotoView
import android.widget.TextView
import com.mgm.kiliaro.generals.extensions.PhotoFullPopupWindow
import android.widget.ImageButton
import android.graphics.drawable.BitmapDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.DiskCacheStrategy
import android.view.Gravity
import android.view.View
import android.widget.PopupWindow
import android.widget.ProgressBar
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.request.target.Target

/**
 * Created by Majid-Golmoradi on 3/16/2022.
 * Email: golmoradi.majid@gmail.com
 */
class PhotoFullPopupWindow(
    ctx: Context,
    layout: Int,
    v: View?,
    imageUrl: String?,
    bitmap: Bitmap?,
    createAt: String?
) : PopupWindow(
    (ctx.getSystemService(
        Context.LAYOUT_INFLATER_SERVICE
    ) as LayoutInflater).inflate(R.layout.popup_photo_full, null),
    ViewGroup.LayoutParams.MATCH_PARENT,
    ViewGroup.LayoutParams.MATCH_PARENT
) {
    var view: View
    var mContext: Context
    var photoView: PhotoView
    var loading: ProgressBar
    var txtCreateAt: TextView
    var parent: ViewGroup

    companion object {
        private val instance: PhotoFullPopupWindow? = null
    }

    init {
        elevation = 5.0f
        mContext = ctx
        view = contentView
        val closeButton = view.findViewById<ImageButton>(R.id.ib_close)
        isOutsideTouchable = true
        isFocusable = true
        // Set a click listener for the popup window close button
        closeButton.setOnClickListener { // Dismiss the popup window
            dismiss()
        }
        //---------Begin customising this popup--------------------
        photoView = view.findViewById<View>(R.id.image) as PhotoView
        loading = view.findViewById<View>(R.id.loading) as ProgressBar
        txtCreateAt = view.findViewById<View>(R.id.txtCreateAt) as TextView
        photoView.maximumScale = 6f
        parent = photoView.parent as ViewGroup
        // ImageUtils.setZoomable(imageView);
        //----------------------------
        if (bitmap != null) {
            parent.background = BitmapDrawable(
                mContext.resources,
                fastBlur(Bitmap.createScaledBitmap(bitmap, 50, 50, true))
            ) // ));
            photoView.setImageBitmap(bitmap)
        }
        loading.isIndeterminate = true
        loading.visibility = View.VISIBLE
        Glide.with(ctx).asBitmap()
            .load(imageUrl)
            .error(R.drawable.ic_launcher_background)
            .listener(object : RequestListener<Bitmap?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Bitmap?>,
                    isFirstResource: Boolean
                ): Boolean {
                    loading.isIndeterminate = false
                    loading.setBackgroundColor(Color.LTGRAY)
                    loading.snackToast("Oops...Try Again")
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any,
                    target: Target<Bitmap?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    parent.background = BitmapDrawable(
                        mContext.resources, fastBlur(
                            Bitmap.createScaledBitmap(
                                resource!!, 50, 50, true
                            )
                        )
                    ) // ));
                    photoView.setImageBitmap(resource)
                    loading.visibility = View.GONE
                    txtCreateAt.text = createAt
                    return false
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(photoView)
        showAtLocation(v, Gravity.CENTER, 0, 0)

        //------------------------------
    }
}