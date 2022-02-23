package com.cupist.glam.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cupist.glam.databinding.ItemUserCardBinding
import com.cupist.glam.network.model.User

class UserCardAdapter: RecyclerView.Adapter<UserCardAdapter.UserCardViewHolder>() {
    private val items: ArrayList<User> = ArrayList()

    inner class UserCardViewHolder(private val binding: ItemUserCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: User) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCardViewHolder {
        ItemUserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
            return UserCardViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: UserCardViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clearItems() {
        notifyItemRangeRemoved(0, itemCount)
        items.clear()
    }

    fun addItem(item: User) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}