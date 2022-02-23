package com.cupist.glam.domain.usecase

import com.cupist.glam.domain.repository.PersonRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class PersonUseCase @Inject constructor(private val personRepository: PersonRepository){
    suspend operator fun invoke() = personRepository.getTodayRecommendList()
}