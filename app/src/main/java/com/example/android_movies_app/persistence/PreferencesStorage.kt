package com.example.android_movies_app.persistence

import android.content.Context


private const val PREF_CATEGORY = "pref_category"
private const val PREF_QUERY = "pref_query"

object PreferencesStorage {

    fun getStoredQuery(context: Context): String? {
        val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreference(context)
    }

}