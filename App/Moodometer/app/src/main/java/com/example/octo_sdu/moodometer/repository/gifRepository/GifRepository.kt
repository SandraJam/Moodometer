package com.example.octo_sdu.moodometer.repository.gifRepository

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GifRepository () {
    val apiKey = "dc6zaTOxFJmzC"
    val apiKeyPopKey = "ZGVtbzplYTdiNjZmYjVlNjZjNjJkNmNmYTQ5ZmJlMGYyN2UwMDJjMjUxNGVlZDljNzVlYTlmNjVlOWQ3NTk4Y2I5YTkw"
    val call: CallGiphy = Retrofit.Builder().baseUrl(CallGiphy.baseUrl).addConverterFactory(MoshiConverterFactory.create()).build().create(CallGiphy::class.java)

    fun gifBadTime(): String = gifCall("cute kitty")

    fun gifAverageTime(): String = gifCall("cute puppy")

    fun gifCoolTime(): String = gifCall("cool")

    fun gifBossTime(): String = gifCall("like a boss")

    fun gifWelcome(): String = gifCall("welcome")

    fun gifCall(value: String) = call.call(value, apiKey).execute().body().data!!.fixed_height_downsampled_url

}