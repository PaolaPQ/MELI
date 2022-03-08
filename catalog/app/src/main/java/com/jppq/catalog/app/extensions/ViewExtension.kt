package com.jppq.catalog.app.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun View.toGone() {
    visibility = View.GONE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun ImageView.loadImage(
    url: String,
    context: Context,
    @DrawableRes placeholder: Int,
    @DrawableRes onError: Int = placeholder
) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .error(onError)
        .placeholder(placeholder)
        .skipMemoryCache(true)
        .into(this)
}