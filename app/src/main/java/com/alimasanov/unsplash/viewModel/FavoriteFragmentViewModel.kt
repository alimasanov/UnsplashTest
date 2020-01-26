package com.alimasanov.unsplash.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alimasanov.unsplash.App
import com.alimasanov.unsplash.model.roomObj.PhotoRoom
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

class FavoriteFragmentViewModel: ViewModel() {

    val listPhoto = MutableLiveData<List<PhotoRoom>>().apply {
        App.db.photoDao().getListPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<List<PhotoRoom>> {
                override fun accept(t: List<PhotoRoom>) {
                    value = t
                }
            })
    }
}