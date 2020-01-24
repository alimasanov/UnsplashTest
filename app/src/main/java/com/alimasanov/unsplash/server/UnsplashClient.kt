package com.alimasanov.unsplash.server

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UnsplashClient {
    private var retrofit: Retrofit? = null
    fun getUnsplashClient(): Retrofit {
        if(retrofit == null) {
           val client: OkHttpClient = OkHttpClient
               .Builder()
               .addInterceptor(HInterceptor(Config.ACCESS_KEY))
               .build()
            retrofit = Retrofit.Builder()
                .baseUrl(Config.URL_UNSPLASH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}