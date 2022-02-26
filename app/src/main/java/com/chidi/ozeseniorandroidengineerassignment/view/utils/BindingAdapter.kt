package com.chidi.ozeseniorandroidengineerassignment.view.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter(
    "srcUrl",
    requireAll = false // make the attributes optional
)
fun ImageView.bindSrcUrl(
    url: String?
) = this.load(url) {
    crossfade(true)
}