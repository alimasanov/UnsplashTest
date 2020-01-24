package com.alimasanov.unsplash.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UnsplashDB(context: Context?): SQLiteOpenHelper(
    context,
    DB_NAME, null,
    DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createUnsplashTable = "CREATE TABLE $TABLE_NAME (                    " +
                                    "   $COLUMN_ID              INTEGER PRIMARY KEY,    " +
                                    "   $COLUMN_PHOTO_ID        TEXT,                   " +
                                    "   $COLUMN_TIMESTAMP       TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"

        db.execSQL(createUnsplashTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "UnsplashSavedPhoto.db"
        const val DB_VERSION = 1

        const val TABLE_NAME = "Photos"
        const val COLUMN_ID = "ID"
        const val COLUMN_PHOTO_ID = "ID_Photo"
        const val COLUMN_TIMESTAMP = "timestamp"
    }
}
