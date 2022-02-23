package com.cupist.glam.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    var list: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

    fun addFragment(fragment: Fragment) {
        list.add(fragment)
        notifyItemInserted(list.size - 1)
    }

    fun removeFragment() {
        list.removeLast()
        notifyItemRemoved(list.size)
    }
}