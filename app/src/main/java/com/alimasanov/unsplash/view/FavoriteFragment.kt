package com.alimasanov.unsplash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.adapters.DBAdapter
import com.alimasanov.unsplash.viewModel.FavoriteFragmentViewModel

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.fav_rv)
        val favoriteFragmentViewModel = ViewModelProviders.of(this)
            .get(FavoriteFragmentViewModel::class.java)

        favoriteFragmentViewModel.listPhoto.observe(activity!!, Observer {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                val dbAdapter = DBAdapter(context, it)
                adapter = dbAdapter
            }
        })

        return root
    }
}