package com.example.composedemo.network

import com.example.composedemo.data.Hotel
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getRequest(
        @Url api: String?,
        @Query("_start") start: String?,
        @Query("_limit") limit: String?
    ): Response<List<PostModel>>


    @GET
    suspend fun getHotelsRequest(
        @Url api: String?
    ): Response<List<Hotel>>
}


data class PostModel (
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null,
    @SerializedName("userId")
    var userId: Int = 0
)

