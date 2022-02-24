package com.cupist.glam.domain.usecase

import com.cupist.glam.domain.repository.ProfileRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ProfileUseCase @Inject constructor(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke() = profileRepository.getProfileInfo()
}