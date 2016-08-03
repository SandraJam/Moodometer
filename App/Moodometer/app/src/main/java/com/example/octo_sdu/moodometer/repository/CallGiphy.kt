package com.example.octo_sdu.moodometer.repository

import com.example.octo_sdu.moodometer.repository.pojo.Gif

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CallGiphy {

    @GET("v1/gifs/random?")
    fun call(@Query("tag") item: String, @Query("api_key") apiKey: String): Call<Gif>

    companion object {
        val baseUrl = "http://api.giphy.com/"
    }
}
