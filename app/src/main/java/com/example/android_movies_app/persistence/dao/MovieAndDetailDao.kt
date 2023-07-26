package com.example.android_movies_app.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android_movies_app.ui.models.Movie

@Dao
interface MovieAndDetailDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(vararg movie: Movie):LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update
    fun updateMovies(movie: Movie)

    @Query("SELECT * FROM movie WHERE category=:category LIMIT (:pageNumber*20)")
    fun getMovies(pageNumber: Int, category): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :query || LIMIT (:pageNumber*20)")
    fun searchListMovie(query: String, pageNumber: Int): LiveData<List<Movie>>

}