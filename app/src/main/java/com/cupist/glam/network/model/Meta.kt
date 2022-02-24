package com.cupist.glam.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meta(
    var next: Next?,
    @SerializedName("body_types")
    var bodyTypes: ArrayList<KeyNamePair>,
    var educations: ArrayList<KeyNamePair>,
    var genders: ArrayList<KeyNamePair>,
    @SerializedName("height_range")
    var heightRange: HeightRange
): Parcelable {
    @Parcelize
    data class Next(
        var id: Int,
        var method: String,
        var url: String
    ): Parcelable

    @Parcelize
    data class KeyNamePair(
        var key: String,
        var name: String
    ): Parcelable

    @Parcelize
    data class HeightRange(
        var min: Int,
        var max: Int
    ): Parcelable
}