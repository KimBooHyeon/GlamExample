package com.cupist.glam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.cupist.glam.databinding.FragmentHomeRecommendBinding
import com.cupist.glam.repo.PersonRepository
import com.cupist.glam.vm.PersonVM
import com.cupist.glam.vm.PersonViewModelFactory

class HomeRecommendFragment: Fragment() {

//    private val vm: PersonVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeRecommendBinding.inflate(inflater, container, false)
        val viewModelFactory = PersonViewModelFactory(PersonRepository())
        val viewModel = ViewModelProvider(this, viewModelFactory)[PersonVM::class.java]
        binding.vm = viewModel
        return binding.root
    }
}