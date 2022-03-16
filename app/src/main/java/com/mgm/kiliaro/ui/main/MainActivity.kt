package com.mgm.kiliaro.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import com.mgm.kiliaro.R
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.databinding.ActivityMainBinding
import com.mgm.kiliaro.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

import com.mgm.kiliaro.generals.extensions.PhotoFullPopupWindow
import android.graphics.drawable.BitmapDrawable

import android.graphics.Bitmap







@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)


        viewModel.getSharedMedia()

        viewModel.sharedMedia.observe(this) {
            Log.i("MGM", "res")
            setupGridView(it)
        }


    }

    private fun setupGridView(list: ArrayList<ShareMediaResponse>) {
        val adapter = ImageListAdapter(this, R.layout.list_item, list)
        binding.gridview.adapter = adapter

        binding.gridview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val imageView = v.findViewById<ImageView>(R.id.thumbnail)
                val bm = (imageView.drawable as BitmapDrawable).bitmap
                // Code to show image in full screen:
                PhotoFullPopupWindow(this, R.layout.popup_photo_full, v, list[position].download_url, null, list[position].created_at)
            }
    }

}