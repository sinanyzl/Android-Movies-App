package com.example.android_movies_app.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (
    @SerializedName("name")
    val name: String
        ): Parcelable