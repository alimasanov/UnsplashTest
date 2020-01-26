package com.alimasanov.unsplash.model.roomObj

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UnsplashPhoto")
class PhotoRoom {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo(name = "photoID")
    var photoID: String = ""
    @ColumnInfo(name = "SmallURL")
    var smallUrl: String = ""
    @ColumnInfo(name = "RegularURL")
    var regularUrl: String = ""
}