package com.cupist.glam.view.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupist.glam.domain.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonVM @Inject constructor(private val personUseCase: PersonUseCase): ViewModel() {

    init {
        getTodayRecommendList()
    }

    fun getTodayRecommendList() =
        viewModelScope.launch {
            personUseCase().let {
                if (it.isSuccessful) {
                    Log.d("asdf", it.body()?.data.toString())
                }
            }
        }
}