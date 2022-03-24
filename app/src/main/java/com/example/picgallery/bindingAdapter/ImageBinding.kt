package com.example.picgallery.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.picgallery.utils.AppConst
import javax.inject.Singleton


@Singleton
object ImageBinding {
    private const val BASE_PATH = AppConst.BASE_PATH
    @JvmStatic
    @BindingAdapter("id", "server", "secret")
    fun bindImage(image: ImageView, id: String, server: String, secret: String) {
        val imagePath = BASE_PATH + server + "/" + id + "_" + secret + ".jpg".trim()
        Glide.with(image.context)
            .load(imagePath)
            .into(image)
    }
}
