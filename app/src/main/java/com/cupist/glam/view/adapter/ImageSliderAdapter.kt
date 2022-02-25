package com.cupist.glam.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupist.glam.databinding.ItemSliderImageBinding
import com.cupist.glam.imageRound
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSliderAdapter: SliderViewAdapter<ImageSliderAdapter.SliderViewHolder>() {
    private val items: ArrayList<String> = ArrayList()

    inner class SliderViewHolder(private val binding: ItemSliderImageBinding): SliderViewAdapter.ViewHolder(binding.root) {
        fun bindView(src: String) {
            binding.picture.imageRound(src)
        }
    }

    fun setItems(pictures: ArrayList<String>) {
        items.clear()
        items.addAll(pictures)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        ItemSliderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
            return SliderViewHolder(it)
        }
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        viewHolder.bindView(items[position])
    }
}