package com.alimasanov.unsplash.server

import com.alimasanov.unsplash.model.pojo.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkEndpoints {
    @GET("/photos/random")
    fun getRandomPhotos(
        @Query("count") count: Int
    ): Call<ArrayList<Photo>>
}