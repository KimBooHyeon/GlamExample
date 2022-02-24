package com.cupist.glam.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupist.glam.domain.usecase.ProfileUseCase
import com.cupist.glam.network.model.Meta
import com.cupist.glam.network.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor(private val profileUseCase: ProfileUseCase): ViewModel() {

    private val _profileData = MutableLiveData<Profile>()
    val profileData: LiveData<Profile>
        get() = _profileData

    val generalInfo = MutableLiveData<Meta>()

    fun getProfileInfo() =
        viewModelScope.launch {
            profileUseCase().let { res ->
                if (res.isSuccessful) {
                    res.body()?.let {
                        _profileData.value = it.data
                        generalInfo.value = it.meta
                    }
                }
            }
        }

    fun setValue(value: Profile) {
        _profileData.value = value
    }
}