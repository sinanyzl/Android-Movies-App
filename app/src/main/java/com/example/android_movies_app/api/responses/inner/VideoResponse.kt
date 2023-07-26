package com.example.android_movies_app.api.responses.inner

import com.google.gson.annotations.SerializedName
import com.example.android_movies_app.ui.models.Video

class VideoResponse {

    @SerializedName("result")
    val videos:List<Video>?=null

}