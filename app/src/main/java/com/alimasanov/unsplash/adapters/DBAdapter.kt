package com.alimasanov.unsplash.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.model.pojo.Photo
import com.alimasanov.unsplash.model.roomObj.PhotoRoom
import com.alimasanov.unsplash.view.FullScreenActivity
import com.squareup.picasso.Picasso

class DBAdapter(private val context: Context?,
                private val photoList: List<PhotoRoom>): RecyclerView.Adapter<DBAdapter.DBViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DBViewHolder(LayoutInflater
                        .from(context)
                        .inflate(R.layout.photo_item, parent, false))

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        val photoRoom = photoList[position]

        Picasso.get()
            .load(photoRoom.smallUrl)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(holder.card_image)

        holder.itemView.setOnClickListener {
            val photo = Photo()
            photo.id = photoRoom.photoID
            photo.urls.small = photoRoom.smallUrl
            photo.urls.regular = photoRoom.regularUrl
            val intent = Intent(it.context, FullScreenActivity::class.java)
            intent.putExtra("photo", photo)
            it.context!!.startActivity(intent)
        }
    }

    class DBViewHolder(itemView: View,
                       val card_image: ImageView = itemView.findViewById(R.id.card_image)):
        RecyclerView.ViewHolder(itemView)
}