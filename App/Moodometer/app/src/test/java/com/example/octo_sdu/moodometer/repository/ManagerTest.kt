package com.example.octo_sdu.moodometer.repository

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import proto.moodometer.Mood

class ManagerTest {
    val manager = Manager()

    @Before
    fun setUp() {

    }

    @Test
    fun testCreateMoodWhenOk(){
        val mood = Mood.Builder().mind("Ok, I'm fine").date(0).score(4.5f).build()
        assertEquals(mood, manager.createMood(4.5f, "Ok, I'm fine", 0))
    }

    @Test
    fun testAvgMoodWhenListEmpty(){
        val list = listOf<Mood>()
        assertEquals(0.0f, manager.avgMoods(list))
    }

    @Test
    fun testAvgMoodWhenList(){
        val list = listOf<Mood>(Mood.Builder().score(1.0f).build(), Mood.Builder().score(5.0f).build(), Mood.Builder().score(4.5f).build())
        assertEquals(3.5f, manager.avgMoods(list))
    }

    @Test
    fun testAvgMoodWhenResultIsNotCool(){
        val list = listOf<Mood>(Mood.Builder().score(1.0f).build(), Mood.Builder().score(5.0f).build(), Mood.Builder().score(4.0f).build())
        assertEquals(3.3f, manager.avgMoods(list))
    }

    @Test
    fun testgifSentence0(){
        assertEquals("Le moral c'est pas ça? Voici une dose de chaton:", manager.gifSentence(0.0f))
    }

    @Test
    fun testgifSentence2_1(){
        assertEquals("Le moral c'est pas ça? Voici une dose de chaton:", manager.gifSentence(2.1f))
    }

    @Test
    fun testgifSentence3(){
        assertEquals("Plutôt moyen ces derniers jours... Cadeau:", manager.gifSentence(3.0f))
    }

    @Test
    fun testgifSentence4_1(){
        assertEquals("Wooh, tout roule en fait? Like a boss!", manager.gifSentence(4.1f))
    }
}