package com.cupist.glam.repo

import com.cupist.glam.network.DataRequest

class PersonRepository {
    private val client = DataRequest.api()

    suspend fun getTodayRecommendList() = client.getTodayRecommendList()
}