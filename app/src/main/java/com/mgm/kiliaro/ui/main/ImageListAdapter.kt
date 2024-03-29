package com.mgm.kiliaro.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mgm.kiliaro.R
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.databinding.ListItemBinding
import com.mgm.kiliaro.generals.bytesToMeg

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
internal class ImageListAdapter internal constructor(
    context: Context,
    private val resource: Int,
    private val itemList: ArrayList<ShareMediaResponse>?
) : ArrayAdapter<ImageListAdapter.ItemViewHolder>(context, resource) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var itemBinding: ListItemBinding

    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList.size else 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val holder: ItemViewHolder
        if (convertView == null) {
            itemBinding = ListItemBinding.inflate(inflater)
            convertView = itemBinding.root
            holder = ItemViewHolder()
            holder.size = itemBinding.size
            holder.thumbnail = itemBinding.thumbnail
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemViewHolder
        }

        holder.size!!.text = bytesToMeg((this.itemList!![position]).size)
//        holder.icon!!.setImageResource(R.mipmap.ic_launcher)
        val thumbnail = (this.itemList[position]).thumbnail_url.plus("?w=200&h=200&m=crop")
        Glide.with(context).load(thumbnail).placeholder(R.drawable.progress_animation)
            .dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.thumbnail!!)
        return convertView
    }

    internal class ItemViewHolder {
        var size: TextView? = null
        var thumbnail: ImageView? = null
    }
}
