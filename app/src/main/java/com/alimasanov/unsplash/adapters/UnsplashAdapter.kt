package com.alimasanov.unsplash.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.App
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.model.pojo.Photo
import com.alimasanov.unsplash.model.roomObj.PhotoRoom
import com.alimasanov.unsplash.view.FullScreenActivity
import com.squareup.picasso.Picasso

class UnsplashAdapter(private val context: Context?,
                      private var photos: List<Photo>?):
    RecyclerView.Adapter<UnsplashAdapter.UnsplashViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = UnsplashViewHolder(LayoutInflater
                                .from(parent.context)
                                .inflate(R.layout.photo_item, parent, false))

    override fun getItemCount(): Int {
        return photos!!.count()
    }

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        val photo: Photo = photos!![position]

        Picasso.get()
            .load(photo.urls.small)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(holder.card_image)

        holder.itemView.setOnLongClickListener{
            val db = App.db
            val photoRoom = PhotoRoom()
            photoRoom.photoID = photo.id!!
            photoRoom.smallUrl = photo.urls.small!!
            photoRoom.regularUrl = photo.urls.regular!!

            db.photoDao().insertAll(photoRoom)
            Toast.makeText(context, "Фото добавлено в избранное", Toast.LENGTH_SHORT).show()
            true
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, FullScreenActivity::class.java)
            intent.putExtra("photo", photo)
            it.context.startActivity(intent)
        }
    }

    class UnsplashViewHolder(itemView: View,
                             val card_image: ImageView = itemView.findViewById(R.id.card_image)):
        RecyclerView.ViewHolder(itemView)
}