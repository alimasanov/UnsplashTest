package com.alimasanov.unsplash.adapters

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.db.UnsplashDB
import com.alimasanov.unsplash.server.PhotoOperations
import com.alimasanov.unsplash.model.Photo
import com.alimasanov.unsplash.view.FullScreenActivity
import java.io.Serializable

class UnsplashAdapter(private val context: Context?,
                      private var photos: List<Photo>?,
                      private val db: SQLiteDatabase):
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
        PhotoOperations().initCard(photo,
            holder.card_image,
            holder.card_desc,
            holder.card_location,
            holder.rl_main)

        holder.itemView.setOnLongClickListener{
            val cv = ContentValues()
            cv.put(UnsplashDB.COLUMN_PHOTO_ID, photo.id)
            db.insert(UnsplashDB.TABLE_NAME, null, cv)
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
                             val card_image: ImageView = itemView.findViewById(R.id.card_image),
                             val card_desc: TextView = itemView.findViewById(R.id.card_desc),
                             val card_location: TextView = itemView.findViewById(R.id.card_location),
                             val rl_main: RelativeLayout = itemView.findViewById(R.id.ll_main)):
        RecyclerView.ViewHolder(itemView)
}