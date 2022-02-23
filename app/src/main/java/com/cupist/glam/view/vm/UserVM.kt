package com.cupist.glam.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupist.glam.domain.usecase.UserAdditionalRecommendUseCase
import com.cupist.glam.domain.usecase.UserDynamicLinkUseCase
import com.cupist.glam.domain.usecase.UserTodayRecommendUseCase
import com.cupist.glam.network.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(
    private val userTodayRecommendUseCase: UserTodayRecommendUseCase,
    private val userAdditionalRecommendUseCase: UserAdditionalRecommendUseCase,
    private val userDynamicLinkUseCase: UserDynamicLinkUseCase,
): ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    var nextUrl: String? = null

    private val _userData = MutableLiveData<List<User>>()
    val userData: LiveData<List<User>>
        get() = _userData

    fun reset() {
        nextUrl = null
        _userData.value = emptyList()
        getTodayRecommendList()
    }

    fun getTodayRecommendList() =
        viewModelScope.launch {
            isLoading.value = true
            userTodayRecommendUseCase().let { res ->
                if (res.isSuccessful) {
                    res.body()?.let { item ->
                        _userData.value = item.data
                        getAdditionalRecommendList()
                    }
                }
                isLoading.value = false
            }
        }

    fun getAdditionalRecommendList() =
        viewModelScope.launch {
            isLoading.value = true
            userAdditionalRecommendUseCase().let { res ->
                if (res.isSuccessful) {
                    res.body()?.let { item ->
                        _userData.value = item.data
                        nextUrl = item.meta.next?.url
                    }
                }
                isLoading.value = false
            }
        }

    fun getDynamicUrlUserList() =
        viewModelScope.launch {
            isLoading.value = true
            nextUrl?.let {
                userDynamicLinkUseCase(it).let { res ->
                    if (res.isSuccessful) {
                        res.body()?.let { item ->
                            _userData.value = item.data
                            nextUrl = item.meta.next?.url
                        }
                    }
                    isLoading.value = false
                }
            }

        }
}