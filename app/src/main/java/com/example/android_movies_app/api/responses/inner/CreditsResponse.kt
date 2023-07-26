package com.example.android_movies_app.api.responses.inner

import com.example.android_movies_app.ui.models.Cast
import com.google.gson.annotations.SerializedName

class CreditsResponse {

    @SerializedName("cast")
    val casts:List<Cast>?= null

}