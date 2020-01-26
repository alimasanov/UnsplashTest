package com.alimasanov.unsplash.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alimasanov.unsplash.App
import com.alimasanov.unsplash.model.roomObj.PhotoRoom

class FavoriteFragmentViewModel: ViewModel() {

    val listPhoto = MutableLiveData<List<PhotoRoom>>().apply {
        App.db.photoDao().getListPhotos()
            .observeOn(AndroidSchedulers.mainThread)
            .subscribe { t -> value = t }
    }

    private fun dbQuery(){

    }
}