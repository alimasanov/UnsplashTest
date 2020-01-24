package com.alimasanov.unsplash.viewModel

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alimasanov.unsplash.model.Photo
import com.alimasanov.unsplash.server.NetworkEndpoints
import com.alimasanov.unsplash.server.UnsplashClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotoFragmentViewModel: ViewModel() {

    private val networkEndpoints: NetworkEndpoints = UnsplashClient().getUnsplashClient().create()
    private val photos = networkEndpoints.getRandomPhotos(10)
    private val _listPhoto = MutableLiveData<List<Photo>>()
    val listPhoto: LiveData<List<Photo>> = _listPhoto
    private var photosData: List<Photo>? = ArrayList()

    fun initListPhoto(): MutableLiveData<List<Photo>>? {

            photos.enqueue(object : Callback<List<Photo>> {
                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("callback error", t.localizedMessage)
                }

                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    _listPhoto.value = response.body()
                }
            })

        return _listPhoto
    }
}