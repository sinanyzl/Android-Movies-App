package com.example.android_movies_app.repository

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.example.android_movies_app.api.MoviesApi
import com.example.android_movies_app.api.responses.ApiEmptyResponse
import com.example.android_movies_app.api.responses.ApiErrorResponse
import com.example.android_movies_app.api.responses.ApiResponse
import com.example.android_movies_app.api.responses.ApiSuccessResponse
import com.example.android_movies_app.api.responses.MovieDetailsResponse
import com.example.android_movies_app.models.MovieDetails
import com.example.android_movies_app.util.NetworkBoundResourceNoCaching
import com.example.android_movies_app.util.Resource
import java.util.ResourceBundle

class MovieDetailRepository( private val movieApi: MoviesApi) {


    fun getMovieDetail(movieId: Long): LiveData<Resource<MovieDetails>> {
        return object : NetworkBoundResourceNoCaching<MovieDetails, MovieDetailsResponse>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<MovieDetailsResponse>) {
                val detailsResponse= response.body
                val videos = detailsResponse.videoResponse?.videos
                val casts = detailsResponse.creditsResponse?.casts
                val reviews = detailsResponse.reviewResponse?.reviews
                result.value = Resource.Success(MovieDetails(videos, reviews, casts))
            }

            override fun handleApiEmptyResponse(response: ApiEmptyResponse<MovieDetailsResponse>) {
                //MovieDetails,has empty videos,casts,reviews
                result.value = Resource.Success(MovieDetails())
            }

            override fun handleApiErrorResponse(response: ApiErrorResponse<MovieDetailsResponse>) {
                result.value = Resource.Error(response.errorMessage,null)
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailsResponse>> =
                movieApi.getMovieDetail(movieId)

        }.asLiveData()
    }
}