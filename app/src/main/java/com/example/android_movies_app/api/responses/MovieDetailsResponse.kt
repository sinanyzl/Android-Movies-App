package com.example.android_movies_app.api.responses

import com.example.android_movies_app.api.responses.inner.CreditsResponse
import com.example.android_movies_app.api.responses.inner.ReviewsResponse
import com.example.android_movies_app.api.responses.inner.VideoResponse
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("videos")
    val videoResponse: VideoResponse? = null,

    @SerializedName("reviews")
    val reviewsResponse: ReviewsResponse? = null,

    @SerializedName("credits")
    val creditsResponse: CreditsResponse? = null
)