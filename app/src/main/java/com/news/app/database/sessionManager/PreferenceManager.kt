package com.news.app.database.sessionManager

import android.content.Context
import com.news.app.utils.Constants.prefName


class PreferenceManager constructor(val context: Context) {


    fun saveBoolean(key: String, value: Boolean) {
        context
            .getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return context
            .getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }

    fun saveString(key: String, value: String) {
        context
            .getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun getString(key: String): String {
        return context
            .getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getString(key, "")!!
    }



}