package com.alimasanov.unsplash.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alimasanov.unsplash.model.pojo.Photo
import com.alimasanov.unsplash.server.NetworkEndpoints
import com.alimasanov.unsplash.server.UnsplashClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotoFragmentViewModel: ViewModel() {

    private val networkEndpoints: NetworkEndpoints = UnsplashClient().getUnsplashClient().create()
    private val photos = networkEndpoints.getRandomPhotos(10)

    val listPhoto = initListPhoto()

    private fun initListPhoto(): MutableLiveData<List<Photo>>? {
        val listPhotoMut = MutableLiveData<List<Photo>>()
        photos.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                listPhotoMut.value = response.body()
            }
        })

        return listPhotoMut
    }
}