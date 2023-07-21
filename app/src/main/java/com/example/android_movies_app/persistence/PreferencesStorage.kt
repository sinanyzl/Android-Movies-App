package com.example.android_movies_app.persistence

import android.content.Context
import com.example.android_movies_app.util.Category


private const val PREF_CATEGORY = "pref_category"
private const val PREF_QUERY = "pref_query"

object PreferencesStorage {

    fun getStoredQuery(context: Context): String? {
        val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreference(context)
        returm prefs.getString(PREF_QUERY, null)
    }

    fun setStoredQuery(context: Context, value: String?){
        androidx.preference.PreferenceManager.getDefaultSharedPreference(context)
            .edit()
            .putString(PREF_QUERY, value)
            .apply()
    }

    fun getStoredCategory(context: Context): Category{
        val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreference(context)
        val valueString = prefs.getString(PREF_CATEGORY, Category.POPULAR.toString())
        return when(valueString){
            Category.TOPRATED.toString() -> Category.TOPRATED
            Category.UPCOMING.toString() -> Category.UPCOMING
            else -> Category.POPULAR
        }
    }

    fun setStoredCategory(context: Context, value: Category){
        val valueString = value.toString()
        androidx.preference.PreferenceManager.getDefaultSharedPreference(context)
            .edit()
            .putString(PREF_CATEGORY, valueString)
            .apply()
    }

}