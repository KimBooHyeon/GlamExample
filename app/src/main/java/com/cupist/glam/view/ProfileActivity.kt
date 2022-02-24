package com.cupist.glam.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cupist.glam.R
import com.cupist.glam.custom.CustomListDialog
import com.cupist.glam.databinding.ActivityProfileBinding
import com.cupist.glam.network.model.Meta
import com.cupist.glam.utils.Constants
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
    }

    private fun init() {
        vm.getProfileInfo()
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.tvHeight.setOnClickListener {
            setDialog(Constants.DIALOG_TYPE_HEIGHT)
        }
        binding.tvBodyType.setOnClickListener {
            setDialog(Constants.DIALOG_TYPE_BODY_TYPE)
        }
        binding.tvJob.setOnClickListener {
            setDialog(Constants.DIALOG_TYPE_JOB)
        }
        binding.tvEducation.setOnClickListener {
            setDialog(Constants.DIALOG_TYPE_EDUCATION)
        }
    }

    private fun setDialog(type: Int) {
        val dialog = CustomListDialog(this)
        dialog.setType(type)
        when(type) {
            Constants.DIALOG_TYPE_BODY_TYPE -> dialog.setList(vm.generalInfo.value!!.bodyTypes)
            Constants.DIALOG_TYPE_JOB -> dialog.setList(Constants.DIALOG_LIST_JOB)
            Constants.DIALOG_TYPE_EDUCATION -> dialog.setList(vm.generalInfo.value!!.educations)
            Constants.DIALOG_TYPE_HEIGHT -> {
                val list: ArrayList<Meta.KeyNamePair> = ArrayList()
                val min = vm.generalInfo.value!!.heightRange.min
                val max = vm.generalInfo.value!!.heightRange.max
                list.add(Meta.KeyNamePair("${min - 1}", "${min}cm 미만"))
                for(i: Int in min until max) {
                    list.add(Meta.KeyNamePair("$i", "${i}cm"))
                }
                list.add(Meta.KeyNamePair("${max + 1}", "${max}cm 초과"))
                dialog.setList(list)
            }
        }
        dialog.setOnItemSelected {
            val profile = vm.profileData

            when(type) {
                Constants.DIALOG_TYPE_HEIGHT -> profile.value!!.height = it.toInt()
                Constants.DIALOG_TYPE_BODY_TYPE -> profile.value!!.bodyType = it
                Constants.DIALOG_TYPE_JOB -> profile.value!!.job = it
                Constants.DIALOG_TYPE_EDUCATION -> profile.value!!.education = it
            }
            vm.setValue(profile.value!!)
        }
        dialog.show()
    }
}