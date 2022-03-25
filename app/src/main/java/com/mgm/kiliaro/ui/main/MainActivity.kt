package com.mgm.kiliaro.ui.main

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import androidx.activity.viewModels
import com.mgm.kiliaro.R
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.databinding.ActivityMainBinding
import com.mgm.kiliaro.generals.extensions.PhotoFullPopupWindow
import com.mgm.kiliaro.generals.extensions.snackToast
import com.mgm.kiliaro.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        viewModel.getSharedMedia()
        viewModel.sharedMedia.observe(this) {
            setupGridView(it)
            binding.swipeRefresh.isRefreshing = false
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.clearAllSharedPrefs()
            setupGridView(arrayListOf<ShareMediaResponse>())
            viewModel.getSharedMedia()
        }

        viewModel.genericError.observe(this) {
            binding.swipeRefresh.isRefreshing = false
            binding.gridview.snackToast(it)
            showState(true)
        }


    }

    private fun setupGridView(list: ArrayList<ShareMediaResponse>) {
        if (list.isEmpty() || list.size == 0) showState(true) else showState(false)

        val adapter = ImageListAdapter(this, R.layout.list_item, list)
        binding.gridview.adapter = adapter

        binding.gridview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                try {
                    val imageView = v.findViewById<ImageView>(R.id.thumbnail)
                    val bm = (imageView.drawable as BitmapDrawable).bitmap
                    // Code to show image in full screen:
                    runOnUiThread {
                        PhotoFullPopupWindow(
                            this,
                            R.layout.popup_photo_full,
                            v,
                            list[position].download_url,
                            bm,
                            list[position].created_at
                        )
                    }
                } catch (ex: ClassCastException) {
                    binding.gridview.snackToast("Please waiting...")
                }
            }
    }

    private fun showState(showEmpty:Boolean){
        if (showEmpty){
            binding.emptyState.visibility = View.VISIBLE
            binding.gridview.visibility = View.GONE
        }else{
            binding.emptyState.visibility = View.GONE
            binding.gridview.visibility = View.VISIBLE
        }
    }

}