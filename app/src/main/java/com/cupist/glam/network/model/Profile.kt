package com.cupist.glam.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    var birthday: String,
    @SerializedName("body_type")
    var bodyType: String,
    var company: String,
    var education: String?,
    var gender: String,
    var height: Int,
    var id: Int,
    var introduction: String,
    var job: String,
    var location: String,
    var name: String,
    var school: String,
    var pictures: ArrayList<String>
): Parcelable

@Parcelize
data class ProfileResponse(
    var data: Profile,
    var meta: Meta
): Parcelable