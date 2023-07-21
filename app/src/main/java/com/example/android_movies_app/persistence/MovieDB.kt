package com.example.android_movies_app.persistence

import androidx.room.Database
import androidx.room.TypeConverters
import com.example.android_movies_app.persistence.dao.*
import com.example.android_movies_app.models.Cast
import com.example.android_movies_app.models.Movie
import com.example.android_movies_app.models.Review
import com.android.myapplication.popularmovies.api.model.Video

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = true
)

@TypeConverters(Converter:: class)
abstract class MovieDB : Database(){
    abstract val movieDao: MovieAndDetailDao
}