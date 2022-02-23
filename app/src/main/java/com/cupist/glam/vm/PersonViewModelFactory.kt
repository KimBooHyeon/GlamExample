package com.cupist.glam.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cupist.glam.repo.PersonRepository

class PersonViewModelFactory(private val personRepo: PersonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PersonRepository::class.java).newInstance(personRepo)
    }
}

