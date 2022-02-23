package com.cupist.glam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cupist.glam.databinding.FragmentHomeThirdBinding

class HomeThirdFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeThirdBinding.inflate(inflater, container, false)
        return binding.root
    }
}