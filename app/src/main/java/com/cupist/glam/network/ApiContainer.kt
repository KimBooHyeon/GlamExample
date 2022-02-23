package com.cupist.glam.network

import com.cupist.glam.network.model.User
import com.cupist.glam.network.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiContainer {
    @GET("/introduction")
    suspend fun getTodayRecommendList(): Response<UserResponse>

    @GET("/introduction/additional/{next}")
    fun getAdditionalRecommendList(
        @Path("next") next: Int?
    ): Response<UserResponse>
}