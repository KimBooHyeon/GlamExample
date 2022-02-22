package com.cupist.glam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cupist.glam.databinding.FragmentHomeFirstBinding

class HomeFirstFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeFirstBinding.inflate(inflater, container, false)
        return binding.root
    }
}