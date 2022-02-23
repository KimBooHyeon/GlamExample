package com.cupist.glam.network.model

data class Person(
    var age: Int,
    var company: String,
    var distance: Long,
    var height: Int,
    var id: Int,
    var introduction: String?,
    var job: String,
    var location: String,
    var name: String,
    var pictures: ArrayList<String>,
)

data class ResponsePerson(
    var data: ArrayList<Person>
)

interface DataRepository {
    fun getTodayRecommendList(): ResponsePerson
}