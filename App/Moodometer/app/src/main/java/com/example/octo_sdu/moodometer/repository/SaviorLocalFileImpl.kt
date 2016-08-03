package com.example.octo_sdu.moodometer.repository

import android.content.Context
import android.util.Log
import proto.moodometer.Mood
import proto.moodometer.Moods
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.*

class SaviorLocalFileImpl(context: Context) : Savior {
    val filename = context.filesDir.path.toString() +"/mood"
    val file = File(filename)
    val apiKey = "dc6zaTOxFJmzC"

    override fun createFile(){
        if (!file.exists()){
            val bos = BufferedOutputStream(FileOutputStream(file))
            bos.write(Moods.Builder().moods(mutableListOf()).build().encode())
            bos.flush()
            bos.close()
        }
    }

    override fun saveMood(mood: Mood):Boolean {
        val bytes: ByteArray = ByteArray(file.length().toInt())
        val buf = BufferedInputStream(FileInputStream(filename))
        buf.read(bytes, 0, bytes.size)
        buf.close()

        val list = mutableListOf(mood)
        list.addAll(Moods.ADAPTER.decode(bytes).moods)

        val bos = BufferedOutputStream(FileOutputStream(file))
        bos.write(Moods.ADAPTER.encode(Moods.Builder().moods(list).build()))
        bos.flush()
        bos.close()

        return true
    }

    override fun getMoods(): List<Mood> {
        val buf = BufferedInputStream(FileInputStream(filename))
        val bytes: ByteArray = ByteArray(file.length().toInt())
        buf.read(bytes, 0, bytes.size)
        buf.close()
        return Moods.ADAPTER.decode(bytes).moods
    }

    override fun getMoodsByTime(l: Long): List<Mood> {
        val buf = BufferedInputStream(FileInputStream(filename))
        val bytes: ByteArray = ByteArray(file.length().toInt())
        buf.read(bytes, 0, bytes.size)
        buf.close()
        val moods = Moods.ADAPTER.decode(bytes).moods

        return moods.filter { it.date > l }
    }

    override fun gifUrl(avg: Float): String {
        val retrofit = Retrofit.Builder().baseUrl(CallGiphy.baseUrl).addConverterFactory(MoshiConverterFactory.create()).build()
        val validCall = retrofit.create(CallGiphy::class.java).call(when{
            avg > 4.5 -> "like a boss"
            avg > 3.5 -> "cool"
            avg > 2 -> "cute puppy"
            else -> "cute kitty"
        }, apiKey)
        val gif = validCall.execute().body()
        if (gif != null && gif.data != null){
            return gif.data!!.fixed_height_downsampled_url
        }
        return ""
    }

}