package com.example.dh3teste.api

import com.example.dh3teste.Model.Comics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {
    @GET("comics")
    suspend fun getComics(
        @Query("offset") offsetNumber: Int
    ): Response<Comics>
}