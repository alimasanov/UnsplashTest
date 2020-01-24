package com.alimasanov.unsplash.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.model.Photo
import com.squareup.picasso.Picasso

class FullScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_activity)

        val photo = intent.getSerializableExtra("photo") as? Photo
        val iv: ImageView = findViewById(R.id.full_image)
        Picasso.get()
            .load(photo!!.urls!!.regular)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(iv)
    }
}