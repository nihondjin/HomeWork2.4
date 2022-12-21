package com.example

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.room.db.AppDataBase
import com.example.room.utils.PreferenceHelper
// Класс живет пока приложение не удалено
class App : Application() {
    companion object {
        private var appDataBase: AppDataBase? = null
        private var context: Context? = null
        fun getInstance(): AppDataBase? {
            if (appDataBase == null) {
                appDataBase = context?.let {
                    Room
                        .databaseBuilder(it, AppDataBase:: class.java,"Note.Database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return appDataBase
        }
    }

    // onCreate он вызывается когда приложение впервые запускается на нашем телефоне
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper
        sharedPreferences.init(this)
        context = applicationContext
        getInstance()
    }
}

// Что такое поток Мейн(это то что мы видим, то есть отображается все тосты все Вьюшки)((allowMainTheadQueries))