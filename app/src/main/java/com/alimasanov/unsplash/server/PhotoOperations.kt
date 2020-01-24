package com.alimasanov.unsplash.server

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.adapters.DBAdapter
import com.alimasanov.unsplash.adapters.UnsplashAdapter
import com.alimasanov.unsplash.db.UnsplashDB
import com.alimasanov.unsplash.model.Photo
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotoOperations {

    fun loadFavoritePhotos(context: Context?): DBAdapter{

        val unsplashDB: UnsplashDB? = UnsplashDB(context)
        val db: SQLiteDatabase = unsplashDB!!.writableDatabase

        val dbAdapter = DBAdapter(context, getAllItems(db))

        return dbAdapter
    }

    private fun getAllItems(db: SQLiteDatabase): Cursor? {
        return db.query(UnsplashDB.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)
    }

    fun loadRandomPhotos(
        context: Context?,
        recyclerView: RecyclerView,
        count: Int){
        val unsplashDB: UnsplashDB? = UnsplashDB(context)
        val db: SQLiteDatabase = unsplashDB!!.writableDatabase

        val networkEndpoints: NetworkEndpoints = UnsplashClient().getUnsplashClient().create()
        val photos = networkEndpoints.getRandomPhotos(count)

        photos.enqueue(object: Callback<List<Photo>>{
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val unsplashAdapter = UnsplashAdapter(context, response.body(), db)
                recyclerView.adapter = unsplashAdapter
            }
        })
    }

    fun getPhotoById(photo_id: String?): Photo?{

        val networkEndpoints: NetworkEndpoints = UnsplashClient().getUnsplashClient().create()
        val callPhoto = networkEndpoints.getPhotoById(photo_id!!)
        var photo: Photo? =
            Photo()

        callPhoto.enqueue(object: Callback<Photo>{
            override fun onFailure(call: Call<Photo>, t: Throwable) {
                Log.d("unsplasherr", t.localizedMessage!!)
            }

            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                photo = response.body()
            }
        })

        return photo
    }

    fun locationNormalize(photo: Photo): String?{
        var locatioon: String? = null

        if (photo.location?.country !== null && photo.location.city !== null)
            locatioon = "${photo.location.country}, ${photo.location.city}"
        else if(photo.location?.country == null && photo.location?.city !== null)
            locatioon = photo.location.city
        else if(photo.location?.country !== null && photo.location.city == null)
            locatioon = photo.location.country

        return locatioon
    }

    fun initCard(photo: Photo?,
                 image: ImageView,
                 description: TextView,
                 location: TextView,
                 rl: RelativeLayout){
        Picasso.get()
            .load(photo!!.urls?.small)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(image)

        val cardDesc: String? = photo.description
        if (cardDesc == null){
            rl.removeView(cardDesc)
        } else description.text = cardDesc

        if(locationNormalize(photo) == null)
            rl.removeAllViews()
        else location.text = locationNormalize(photo)
    }
}