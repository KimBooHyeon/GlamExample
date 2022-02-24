package com.cupist.glam.domain.repository

import com.cupist.glam.network.ApiContainer
import com.cupist.glam.network.model.ProfileResponse
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ApiContainer) {

    suspend fun getProfileInfo(): Response<ProfileResponse> = api.getProfileInfo()
}