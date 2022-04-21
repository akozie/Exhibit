package com.example.seampay.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.seampay.R
import com.example.seampay.model.ImagesList
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExhibitDatabaseTest : TestCase() {

    private lateinit var db:ExhibitDatabase
    private lateinit var dao: ExhibitDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ExhibitDatabase::class.java).build()
        dao = db.exhibitDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun writeAndReadExhibit() = runBlocking {
        val exhibit = Exhibits(1, "IPhone13", ImagesList(listOf(R.drawable.ic_launcher_background.toString(), R.drawable.ic_launcher_background.toString())))
        dao.insertExhibits(listOf(exhibit))
        val getIphone = dao.getExhibits()
        assertThat(getIphone != null).isTrue()
    }
}