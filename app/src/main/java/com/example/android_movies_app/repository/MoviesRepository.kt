package com.example.android_movies_app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android_movies_app.api.MoviesApi
import com.example.android_movies_app.api.responses.ApiResponse
import com.example.android_movies_app.api.responses.MoviesResponse
import com.example.android_movies_app.persistence.dao.MovieAndDetailDao
import com.example.android_movies_app.util.AppExecutors
import com.example.android_movies_app.util.Category
import com.example.android_movies_app.util.NetworkBoundResource
import com.example.android_movies_app.util.Resource
import com.example.android_movies_app.model.Movie
import com.example.android_movies_app.models.Movie
import com.example.android_movies_app.responses.MoviesResponse
import java.util.Locale.Category



class MoviesRepository (
        private val movieDao: MovieAndDetailDao,
        private val appExecutors: AppExecutors,
        private val movieApi: MoviesApi
        ){
        companion object{ private const val TAG = "MoviesRepository"}

        fun getListMovie(pageNumber: Int, category: Category): LiveData<Resource<List<Movie>>> {
                return object : NetworkBoundResource<List<Movie>, MoviesResponse>(appExecutors) {
                        item?.let{
                                val list: ArrayList<Move>? = (item.movies)?.let {ArrayList(it)}
                                val newList: ArrayList<Movie>? = ArrayList<Movie>()
                                list?.forEach {
                                        val movie = it.copy(categoryType = category)
                                        Log.d(TAG, "saveCallResult: ${movie}")
                                        newList?.add(movie)
                                }
                                newList?.let { movieDao.insertMovies(*newList.toTypedArray()) }
                        }
                }

                override fun shouldFetch(data: List<Movie>?): Boolean {
                        return true
                }




        }
}