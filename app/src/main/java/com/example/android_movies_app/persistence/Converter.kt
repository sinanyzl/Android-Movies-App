package com.example.android_movies_app.persistence


import androidx.room.TypeConverter

import com.example.android_movies_app.movies.util.Category

class Converter {

    @TypeConverter
    fun catToInt(cat: Category?): Int?{
        return cat?.ordinal
    }

    @TypeConverter
    fun intToCat(int: Int): Category? {
        return int?.let {
            when(it){
                0 -> Category.POPULAR
                1 -> Category.UPCOMING
                2 -> Category.TOPRATED
                else -> Category.POPULAR
            }
        }
    }
}