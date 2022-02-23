package com.cupist.glam.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupist.glam.network.model.Person
import com.cupist.glam.repo.PersonRepository
import kotlinx.coroutines.launch

class PersonVM (private val repo: PersonRepository): ViewModel() {
    private val _personRepo = MutableLiveData<ArrayList<Person>>()
    val personRepo: LiveData<ArrayList<Person>> = _personRepo

    init {
        getTodayRecommendList()
    }

    fun getTodayRecommendList() {
        viewModelScope.launch {
            repo.getTodayRecommendList().let {
                if (it.isSuccessful) {
                    it.body()?.let {
                        _personRepo.postValue(it.data)
                    }
                }
            }
        }
    }
}