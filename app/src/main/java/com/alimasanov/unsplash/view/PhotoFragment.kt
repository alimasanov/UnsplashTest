package com.alimasanov.unsplash.view

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.adapters.UnsplashAdapter
import com.alimasanov.unsplash.db.UnsplashDB
import com.alimasanov.unsplash.model.Photo
import com.alimasanov.unsplash.server.NetworkEndpoints
import com.alimasanov.unsplash.server.PhotoOperations
import com.alimasanov.unsplash.server.UnsplashClient
import com.alimasanov.unsplash.viewModel.PhotoFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_photo, container, false)
        val photoFragmentViewModel = ViewModelProviders.of(this)
            .get(PhotoFragmentViewModel::class.java)
        val recyclerView: RecyclerView = root.findViewById(R.id.photo_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val unsplashDB: UnsplashDB? = UnsplashDB(context)
        val db: SQLiteDatabase = unsplashDB!!.writableDatabase

        photoFragmentViewModel.initListPhoto()
        photoFragmentViewModel.listPhoto.observe(activity!!, Observer {
            val unsplashAdapter = UnsplashAdapter(context, it, db)
            recyclerView.adapter = unsplashAdapter
        })

        return root
    }

}