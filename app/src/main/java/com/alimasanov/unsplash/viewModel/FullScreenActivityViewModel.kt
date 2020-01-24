package com.alimasanov.unsplash.viewModel

import android.os.Bundle
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

class FullScreenActivityViewModel: ViewModel() {

    private val photoUrl = MutableLiveData<String>().apply {
        value = getPhoto()!!.urls!!.small
    }

    val link: LiveData<String> = photoUrl
    var photoObj: Photo? = Photo()

    private fun getPhoto(): Photo?{
        val networkEndpoints: NetworkEndpoints = UnsplashClient().getUnsplashClient().create()
        val callPhoto = networkEndpoints.getPhotoById(photoObj!!.id)
        var photo: Photo? = Photo()

        callPhoto.enqueue(object: Callback<Photo>{
            override fun onFailure(call: Call<Photo>, t: Throwable) {
                Log.d("CallbackError", t.localizedMessage)
            }

            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                photo = response.body()
            }
        })

        return photo
    }


}