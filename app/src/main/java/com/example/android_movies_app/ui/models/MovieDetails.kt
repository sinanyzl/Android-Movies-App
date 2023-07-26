package com.example.android_movies_app.ui.models

import com.example.android_movies_app.ui.models.Cast
import com.example.android_movies_app.ui.models.Review
import com.example.android_movies_app.ui.models.Video


data class MovieDetails (
    val trailers: List<Video>? = null,
    val reviews: List<Review>? = null,
    val casts: List<Cast>? = null
    )