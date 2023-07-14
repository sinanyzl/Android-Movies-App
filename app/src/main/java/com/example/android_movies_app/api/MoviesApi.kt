package com.example.android_movies_app.api



import android.text.TextUtils
import androidx.lifecycle.LiveData
import com.example.android_movies_app.api.BuildConfig
import com.example.android_movies_app.api.responses.ApiResponse
import com.example.android_movies_app.api.responses.MovieDetailsResponse
import com.example.android_movies_app.api.responses.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/populer")
    fun getPopulerMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): LiveData<ApiResponse<MoviesResponse>>


    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): LiveData<ApiResponse<MoviesResponse>>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): LiveData<ApiResponse<MoviesResponse>>

    @GET("search/movie")
    fun searchMovies(
        @Query("query") query: String? = null,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): LiveData<ApiResponse<MoviesResponse>>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Long,
        @Query("append_to_response") details: String = "videos,credits,reviews",
        @Query("qpi_key") apiKey: String = BuildConfig.API_KEY
    ): LiveData<ApiResponse<MovieDetailsResponse>>
}