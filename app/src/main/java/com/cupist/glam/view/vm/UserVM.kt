package com.cupist.glam.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupist.glam.domain.usecase.UserAdditionalRecommendUseCase
import com.cupist.glam.domain.usecase.UserDynamicLinkUseCase
import com.cupist.glam.domain.usecase.UserPersonalizedRecommendUseCase
import com.cupist.glam.domain.usecase.UserTodayRecommendUseCase
import com.cupist.glam.network.model.User
import com.cupist.glam.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(
    private val userTodayRecommendUseCase: UserTodayRecommendUseCase,
    private val userAdditionalRecommendUseCase: UserAdditionalRecommendUseCase,
    private val userDynamicLinkUseCase: UserDynamicLinkUseCase,
    private val userPersonalizedRecommendUseCase: UserPersonalizedRecommendUseCase,
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    var nextUrl: String? = null

    private val _userData = MutableLiveData<List<User>>()
    val userData: LiveData<List<User>>
        get() = _userData

    private val _personalizedUserData = MutableLiveData<List<User>>()
    val personalizedUserData: LiveData<List<User>>
        get() = _personalizedUserData

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
                        val list: ArrayList<User> = ArrayList()
                        item.data.forEach { user ->
                            user.viewType = Constants.VIEWTYPE_USER_CARD
                            user.isTodayRecommend = true
                            list.add(user)
                        }
                        list.add(
                            User(
                                age=0,
                                company = "",
                                distance = 0,
                                height = 0,
                                id = 0,
                                introduction = null,
                                job = "",
                                location = "",
                                name = "",
                                pictures = arrayListOf(),
                                viewType = Constants.VIEWTYPE_PERSONALIZED_RECOMMEND,
                                isTodayRecommend = false
                            )
                        )
                        _userData.value = list
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
                        val list: ArrayList<User> = ArrayList()
                        item.data.forEach { user ->
                            user.viewType = Constants.VIEWTYPE_USER_CARD
                            list.add(user)
                        }
                        _userData.value = list
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
                            val list: ArrayList<User> = ArrayList()
                            item.data.forEach { user ->
                                user.viewType = Constants.VIEWTYPE_USER_CARD
                                list.add(user)
                            }
                            _userData.value = list
                            nextUrl = item.meta.next?.url
                        }
                    }
                    isLoading.value = false
                }
            }
        }

    fun getPersonalizedRecommendList() =
        viewModelScope.launch {
            isLoading.value = true
            userPersonalizedRecommendUseCase().let { res ->
                if (res.isSuccessful) {
                    res.body()?.let { item ->
                        val list: ArrayList<User> = ArrayList()
                        item.data.forEach { user ->
                            user.viewType = Constants.VIEWTYPE_USER_CARD
                            list.add(user)
                        }
                        _personalizedUserData.value = list
                    }
                }
            }
        }
}