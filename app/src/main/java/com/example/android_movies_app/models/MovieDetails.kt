package com.example.android_movies_app.models

import com.example.android_movies_app.models.Cast
import com.example.android_movies_app.models.Review
import com.example.android_movies_app.models.Video


data class MovieDetails (
    val trailers: List<Video>? = null,
    val reviews: List<Review>? = null,
    val casts: List<Cast>? = null
    )