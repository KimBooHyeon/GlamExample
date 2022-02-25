package com.cupist.glam.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cupist.glam.R
import com.cupist.glam.view.adapter.FragmentAdapter
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
        pagerAdapter.addFragment(HomeRecommendFragment())
        pagerAdapter.addFragment(HomeSecondFragment())
        pagerAdapter.addFragment(HomeThirdFragment())

        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        binding.ivSetting.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
        }

        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.customView = layoutInflater.inflate(R.layout.tab_logo, null)
                1 -> tab.text = "근처"
                2 -> tab.text = "라이브"
                else -> tab.text = "Home"
            }
        }.attach()
    }
}