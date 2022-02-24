package com.cupist.glam.network

import com.cupist.glam.network.model.User
import com.cupist.glam.network.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiContainer {
    @GET("/introduction")
    suspend fun getTodayRecommendList(): Response<UserResponse>

    @GET("/introduction/additional")
    suspend fun getAdditionalRecommendList(): Response<UserResponse>

    @POST("/introduction/custom")
    suspend fun getPersonalizedRecommendList(): Response<UserResponse>

    @GET
    suspend fun getDynamicUserList(@Url url: String): Response<UserResponse>
}