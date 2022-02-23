package com.cupist.glam

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("image")
fun ImageView.image(src: String?) {
    Glide.with(context)
        .load(String.format("%s%s", BuildConfig.BASE_URL, src))
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .apply(RequestOptions.bitmapTransform(CenterCrop()))
        .into(this)
}

@BindingAdapter("image_round")
fun ImageView.imageRound(src: String?) {
    Glide.with(context)
        .load(String.format("%s%s", BuildConfig.BASE_URL, src))
        .apply(
            RequestOptions.bitmapTransform(
                MultiTransformation(
                    CenterCrop(),
                    RoundedCorners(Global.INSTANCE.convertDpToPixel(context, 8))
                )
            )
        )
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

@BindingAdapter("nickname", "age")
fun TextView.setNicknameAndAge(nickname: String, age: Int) {
    text = String.format("%s, %d", nickname, age)
}

@BindingAdapter("job", "distance")
fun TextView.setJobAndDistance(job: String, distance: Long) {
    val dis = if (distance > 1000) {
        String.format("%.1fkm", distance.toFloat() / 1000.0f)
    } else {
        String.format("%dm", distance)
    }
    text = String.format("%s · %s", job, dis)
}

@BindingAdapter("visible")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}