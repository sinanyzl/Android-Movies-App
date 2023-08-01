package com.example.android_movies_app.api.responses.inner


import com.example.android_movies_app.models.Review
import com.google.gson.annotations.SerializedName
class ReviewsResponse {

    @SerializedName("result")
    val reviews:List<Review>?=null

}