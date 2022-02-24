package com.cupist.glam.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cupist.glam.databinding.ItemEmptyBinding
import com.cupist.glam.databinding.ItemPersonalizedRecommendBinding
import com.cupist.glam.databinding.ItemUserCardBinding
import com.cupist.glam.network.model.User
import com.cupist.glam.utils.Constants
import com.cupist.glam.view.vm.UserVM

class UserCardAdapter(private val vm: UserVM): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<User> = ArrayList()

    inner class UserCardViewHolder(private val binding: ItemUserCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(vm: UserVM, user: User) {
            binding.vm = vm
            binding.item = user
        }
    }

    inner class PersonalizedRecommendViewHolder(private val binding: ItemPersonalizedRecommendBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(vm: UserVM) {
            binding.vm = vm
        }
    }

    inner class EmptyViewHolder(binding: ItemEmptyBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            Constants.VIEWTYPE_USER_CARD ->
                ItemUserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
                    return UserCardViewHolder(it)
                }
            Constants.VIEWTYPE_PERSONALIZED_RECOMMEND ->
                ItemPersonalizedRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
                    return PersonalizedRecommendViewHolder(it)
                }
            else -> return EmptyViewHolder(ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            Constants.VIEWTYPE_USER_CARD -> (holder as UserCardViewHolder).bindView(vm, items[position])
            Constants.VIEWTYPE_PERSONALIZED_RECOMMEND -> (holder as PersonalizedRecommendViewHolder).bindView(vm)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    fun clearItems() {
        notifyItemRangeRemoved(0, itemCount)
        items.clear()
    }

    fun addItem(item: User) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItemToFirstPosition(item: User) {
        items.add(0, item)
        notifyItemInserted(0)
    }
}