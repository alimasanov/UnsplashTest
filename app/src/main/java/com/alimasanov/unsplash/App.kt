package com.alimasanov.unsplash

import android.app.Application
import androidx.room.Room
import com.alimasanov.unsplash.db.UnsplashDatabase
import com.alimasanov.unsplash.model.roomObj.PhotoRoom
import io.reactivex.functions.Consumer

class App: Application() {

    companion object {
        lateinit var instance: App
        lateinit var db: UnsplashDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(
                    instance,
                    UnsplashDatabase::class.java,
                    "db")
                .build()
    }
}