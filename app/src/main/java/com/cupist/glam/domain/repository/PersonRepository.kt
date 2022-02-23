package com.cupist.glam.domain.repository

import com.cupist.glam.network.ApiContainer
import com.cupist.glam.network.model.ResponsePerson
import retrofit2.Response
import javax.inject.Inject

class PersonRepository @Inject constructor(private val api: ApiContainer) {

    suspend fun getTodayRecommendList(): Response<ResponsePerson> = api.getTodayRecommendList()
}