package com.cupist.glam

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("image")
fun ImageView.image(src: String?) {
    Glide.with(context)
        .load(String.format("%s%s", BuildConfig.BASE_URL, src))
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .apply(RequestOptions.bitmapTransform(CenterCrop()))
        .into(this)
}

@BindingAdapter("nickname", "age")
fun TextView.setNicknameAndAge(nickname: String, age: Int) {
    text = String.format("%s, %d", nickname, age)
}

@BindingAdapter("job", "distance")
fun TextView.setJobAndDistance(job: String, distance: Long) {
    text = String.format("%s %s", job, distance.toString())
}

@BindingAdapter("visible")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}