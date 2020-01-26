package com.alimasanov.unsplash.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.alimasanov.unsplash.App
import com.alimasanov.unsplash.model.pojo.Photo
import com.alimasanov.unsplash.server.NetworkEndpoints
import com.alimasanov.unsplash.server.UnsplashClient
import com.alimasanov.unsplash.view.PhotoFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotoFragmentViewModel: ViewModel() {

    private val networkEndpoints: NetworkEndpoints = UnsplashClient().getUnsplashClient().create()
    private val photos = networkEndpoints.getRandomPhotos(30)

    val listPhoto = initListPhoto()

    private fun initListPhoto(): MutableLiveData<ArrayList<Photo>>? {
        val listPhotoMut = MutableLiveData<ArrayList<Photo>>()
        photos.enqueue(object : Callback<ArrayList<Photo>>{
            override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {}
            override fun onResponse(call: Call<ArrayList<Photo>>,
                                    response: Response<ArrayList<Photo>>) {
                listPhotoMut.value = response.body()
            }
        })
        return listPhotoMut
    }
}