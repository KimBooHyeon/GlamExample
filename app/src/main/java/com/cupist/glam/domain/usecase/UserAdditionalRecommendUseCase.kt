package com.cupist.glam.domain.usecase

import com.cupist.glam.domain.repository.UserRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UserAdditionalRecommendUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getAdditionalRecommendList()
}