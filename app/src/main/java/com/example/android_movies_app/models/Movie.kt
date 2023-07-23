package com.example.android_movies_app.models

import androidx.room.Entity
import androidx.room.Index
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie", indices =  [Index("id"), Index("category")],
    primaryKeys = ["id", "category"]
)

data class Movie (

        )