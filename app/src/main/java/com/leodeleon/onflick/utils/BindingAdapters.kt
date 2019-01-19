package com.leodeleon.onflick.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object BindingAdapters {

    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String){
        Glide.with(view)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }

}