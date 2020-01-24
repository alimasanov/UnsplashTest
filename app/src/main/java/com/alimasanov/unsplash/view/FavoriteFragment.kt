package com.alimasanov.unsplash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.server.PhotoOperations

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.fav_rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = PhotoOperations().loadFavoritePhotos(context)

        return root
    }
}