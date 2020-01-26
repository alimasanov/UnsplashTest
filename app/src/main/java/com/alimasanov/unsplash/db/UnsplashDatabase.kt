package com.alimasanov.unsplash.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alimasanov.unsplash.model.roomObj.PhotoRoom

@Database(entities = [PhotoRoom::class], version = 1)
abstract class UnsplashDatabase: RoomDatabase(){
    abstract fun photoDao(): PhotoDAO
}