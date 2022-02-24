package com.cupist.glam.view

import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cupist.glam.R
import com.cupist.glam.databinding.ActivityProfileBinding
import com.cupist.glam.view.vm.ProfileVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity: AppCompatActivity() {

    private val vm: ProfileVM by viewModels()
    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.vm = vm
        binding.lifecycleOwner = this

        init()
        setObserve()
    }

    private fun init() {
        vm.getProfileInfo()
    }

    private fun setObserve() {
        vm.profileData.observe(this, {
//            Log.d("asdf", it.name)
        })
    }
}