package com.example.octo_sdu.moodometer.repository.localRepository

import proto.moodometer.Mood
import proto.moodometer.Moods
import java.io.*

class SaviorLocalFileImpl(val file: File) : Savior {

    override fun saveMood(mood: Mood):Boolean {
        if (!file.exists()){
            val bos = BufferedOutputStream(FileOutputStream(file))
            bos.write(Moods.Builder().moods(mutableListOf()).build().encode())
            bos.flush()
            bos.close()
        }
        val bytes: ByteArray = ByteArray(file.length().toInt())
        val buf = BufferedInputStream(FileInputStream(file))
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
        if (!file.exists()){
            return listOf()
        }
        val buf = BufferedInputStream(FileInputStream(file))
        val bytes: ByteArray = ByteArray(file.length().toInt())
        buf.read(bytes, 0, bytes.size)
        buf.close()
        return Moods.ADAPTER.decode(bytes).moods
    }
}