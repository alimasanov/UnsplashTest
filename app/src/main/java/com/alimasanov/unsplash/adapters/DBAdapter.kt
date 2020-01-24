package com.alimasanov.unsplash.adapters

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.db.UnsplashDB
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.server.PhotoOperations
import com.alimasanov.unsplash.view.FullScreenActivity

class DBAdapter(private val context: Context?,
                private val cursor: Cursor?): RecyclerView.Adapter<DBAdapter.DBViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DBViewHolder(LayoutInflater
                        .from(context)
                        .inflate(R.layout.photo_item, parent, false))

    override fun getItemCount(): Int {
        return cursor!!.count
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        if(!cursor!!.moveToPosition(position)) {
            return
        }
        val imageLink: String? = cursor.getString(cursor.getColumnIndex(UnsplashDB.COLUMN_PHOTO_ID))
        val photo = PhotoOperations().getPhotoById(imageLink)

        PhotoOperations().initCard(photo,
            holder.card_image,
            holder.card_desc,
            holder.card_location,
            holder.rl_main)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, FullScreenActivity::class.java)
            intent.putExtra("photo", photo)
            it.context!!.startActivity(intent)
        }
    }

    class DBViewHolder(itemView: View,
                       val card_image: ImageView = itemView.findViewById(R.id.card_image),
                       val card_desc: TextView = itemView.findViewById(R.id.card_desc),
                       val card_location: TextView = itemView.findViewById(R.id.card_location),
                       val rl_main: RelativeLayout = itemView.findViewById(R.id.ll_main)):
        RecyclerView.ViewHolder(itemView)
}