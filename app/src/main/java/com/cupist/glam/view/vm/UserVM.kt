package com.cupist.glam.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupist.glam.domain.usecase.UserUseCase
import com.cupist.glam.network.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(private val userUseCase: UserUseCase): ViewModel() {
    private val _userData = MutableLiveData<List<User>>()
    val userData: LiveData<List<User>>
        get() = _userData

    init {
        getTodayRecommendList()
    }

    fun getTodayRecommendList() =
        viewModelScope.launch {
            userUseCase().let { res ->
                if (res.isSuccessful) {
                    res.body()?.let {
                        _userData.value = it.data
                    }
                }
            }
        }
}