package com.example.composedemo.network

import android.util.Log
import com.example.composedemo.app.ComposeApp
import com.example.composedemo.data.Hotel
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ViewModelScoped
class NetworkManager @Inject constructor() {


    suspend fun getPosts(page: Int): Response<List<PostModel>> {
        val start = (page) * size
        Log.i("main", "getPosts: ${page}    ${start}")
        return retrofit.getRequest(api, start.toString(), size.toString())
    }


    suspend fun getHotels(): Response<List<Hotel>> {
        return retrofit.getHotelsRequest("https://run.mocky.io/v3/eef3c24d-5bfd-4881-9af7-0b404ce09507")
    }


    companion object {

        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ChuckInterceptor(ComposeApp.getInstance())).build()

        private const val url = "https://jsonplaceholder.typicode.com/"
        private const val api = "posts"
        private val retrofit: APIService = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(APIService::class.java)
        private const val size = 10
    }


}