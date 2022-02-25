package com.cupist.glam

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cupist.glam.databinding.ItemProfileImageBinding
import com.cupist.glam.network.model.Meta
import com.smarteist.autoimageslider.SliderView

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

@BindingAdapter("slide_images")
fun SliderView.setImages(pictures: ArrayList<String>) {

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

@BindingAdapter("meta", "height")
fun TextView.setHeight(meta: Meta.HeightRange?, height: Int?) {
    meta?.let { range ->
        height?.let {
            text = when {
                height < range.min -> String.format("%dcm 미만", height)
                height > range.max -> String.format("%dcm 초과", height)
                else -> String.format("%dcm", height)
            }
        }
    }
}

@BindingAdapter("meta", "type")
fun TextView.setType(meta: ArrayList<Meta.KeyNamePair>?, type: String?) {
    meta?.let {
        type?.let {
            text = meta.find { it.key == type }?.name
        }
    }
}

@BindingAdapter("pictures")
fun GridLayout.setPictures(pictures: ArrayList<String>?) {
    removeAllViews()
    for(i: Int in 0 until 6) {
        val binding = ItemProfileImageBinding.inflate(LayoutInflater.from(context), null, false)
        pictures?.let {
            if (i < it.size) {
                binding.ivPicture.image(it[i])
            }
        }
        val span = GridLayout.spec(GridLayout.UNDEFINED,1f)
        val gridParam = GridLayout.LayoutParams(span, span)
        gridParam.width = 0
        gridParam.height = 0
        binding.root.layoutParams = gridParam
        addView(binding.root)
    }
}

@BindingAdapter("visible")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}