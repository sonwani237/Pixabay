package com.exchange.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("imgUrl")
    fun setMapUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}