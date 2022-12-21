package com.example.room.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var sharedPreference: SharedPreferences

    fun init(context: Context) {
        sharedPreference = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var isShow: Boolean
        get() = sharedPreference.getBoolean("isShow", false)
        set(w) = sharedPreference.edit().putBoolean("isShow", w).apply()

    var isLinearLayout: Boolean
        get() = sharedPreference.getBoolean("isLinearLayout", true)
        set(value) = sharedPreference.edit().putBoolean("isLinearLayout", value).apply()
}