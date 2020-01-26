package com.alimasanov.unsplash.db

import androidx.room.*
import com.alimasanov.unsplash.model.roomObj.PhotoRoom
import io.reactivex.Flowable

@Dao
interface PhotoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photoRoom: PhotoRoom)

    @Delete
    fun delete(photoRoom: PhotoRoom)

    @Query("SELECT * FROM UnsplashPhoto")
    fun getListPhotos(): Flowable<List<PhotoRoom>>

    @Query("SELECT * FROM UnsplashPhoto WHERE photoID == :id")
    fun getPhotoById(id: String): Flowable<PhotoRoom>
}