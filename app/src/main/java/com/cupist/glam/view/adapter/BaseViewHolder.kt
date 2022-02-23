package com.cupist.glam.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cupist.glam.network.model.User

abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bindView(user: User)
}