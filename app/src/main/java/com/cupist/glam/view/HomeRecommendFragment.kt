package com.cupist.glam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cupist.glam.databinding.FragmentHomeRecommendBinding
import com.cupist.glam.network.model.User
import com.cupist.glam.view.adapter.UserCardAdapter
import com.cupist.glam.view.vm.UserVM
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeRecommendFragment: Fragment() {

    lateinit var binding: FragmentHomeRecommendBinding
    private val vm: UserVM by viewModels()
    private val adapter by lazy { UserCardAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeRecommendBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this

        init()
        setObserve()
        return binding.root
    }

    private fun init() {
        binding.list.adapter = adapter

        onRefresh()
    }

    private fun setObserve() {
        vm.userData.observe(this, {
            it?.let { res ->
                res.forEach { item ->
                    adapter.addItem(item)
                }
            }
        })
    }

    private fun onRefresh() {
        adapter.clearItems()
        vm.getTodayRecommendList()
    }
}