package com.cupist.glam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cupist.glam.adapter.FragmentAdapter
import com.cupist.glam.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pagerAdapter = FragmentAdapter(requireActivity())
        pagerAdapter.addFragment(HomeFirstFragment())
        pagerAdapter.addFragment(HomeSecondFragment())
        pagerAdapter.addFragment(HomeThirdFragment())

        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "glam"
                1 -> "근처"
                2 -> "라이브"
                else -> "Home"
            }
        }.attach()
    }
}