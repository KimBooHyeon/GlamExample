package com.cupist.glam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cupist.glam.databinding.FragmentHomeRecommendBinding
import com.cupist.glam.view.vm.PersonVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeRecommendFragment: Fragment() {

    private val vm: PersonVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeRecommendBinding.inflate(inflater, container, false)
        binding.vm = vm
        return binding.root
    }
}