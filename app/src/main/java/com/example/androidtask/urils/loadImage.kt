package com.example.androidtask.urils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}

/*
* @BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    // Use Picasso, Glide, or your preferred image-loading library to load the image
    if (imageUrl != null && imageUrl.isNotEmpty()) {
        Picasso.get().load(imageUrl).into(view)
    }
}*/