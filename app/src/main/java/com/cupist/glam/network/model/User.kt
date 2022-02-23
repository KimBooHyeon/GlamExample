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
    var viewType: Int,
    var isTodayRecommend: Boolean
): Parcelable

@Parcelize
data class Meta(
    var next: Next?
): Parcelable {
    @Parcelize
    data class Next(
        var id: Int,
        var method: String,
        var url: String
    ): Parcelable
}

@Parcelize
data class UserResponse(
    var data: List<User>,
    var meta: Meta
): Parcelable