package com.example.android_movies_app.repository

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.example.android_movies_app.api.MoviesApi
import com.example.android_movies_app.models.MovieDetails

class MovieDetailRepository (private val movieApi: MoviesApi) {

    fun getMovieDetail(movieId: Long): LiveData<Resource<MovieDetails>> {
        return object :
    }
}