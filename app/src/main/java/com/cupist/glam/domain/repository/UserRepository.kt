package com.cupist.glam.domain.repository

import com.cupist.glam.network.ApiContainer
import com.cupist.glam.network.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: ApiContainer) {

    suspend fun getTodayRecommendList(): Response<UserResponse> = api.getTodayRecommendList()
}