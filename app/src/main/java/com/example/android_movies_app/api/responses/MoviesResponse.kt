package com.example.android_movies_app.api.responses

import com.example.android_movies_app.ui.models.Movie
import com.google.gson.annotations.SerializedName

class MoviesResponse {

    @SerializedName("page")
    val page:Int = 1

    @SerializedName("total_results")
    val totalResult:Int = 0

    @SerializedName("total_pages")
    val total_pages:Int = 0

    @SerializedName("results")
    val movies:List<Movie>? = null


}