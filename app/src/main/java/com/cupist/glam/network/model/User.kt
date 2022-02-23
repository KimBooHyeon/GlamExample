package com.cupist.glam.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
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
): Parcelable

@Parcelize
data class UserResponse(
    var data: List<User>
): Parcelable