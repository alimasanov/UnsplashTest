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
import com.alimasanov.unsplash.adapters.UnsplashAdapter
import com.alimasanov.unsplash.viewModel.PhotoFragmentViewModel

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

        photoFragmentViewModel.listPhoto!!.observe(activity!!, Observer {
            val unsplashAdapter = UnsplashAdapter(context, it)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = unsplashAdapter
            }
        })

        return root
    }

}