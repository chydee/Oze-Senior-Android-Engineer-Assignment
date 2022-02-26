package com.chidi.ozeseniorandroidengineerassignment.repository.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.repository.TestUtil
import com.chidi.ozeseniorandroidengineerassignment.repository.local.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var database: AppDatabase
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var user: GithubUserModel

    @Before
    fun init() {
        database =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        user = TestUtil.createDummyUser()
    }

    @Test
    fun test_GetsObjectFromDatabase() = runBlocking {
        database.favouriteDao().insert(user)
        val item = database.favouriteDao().getAllFavouriteUsers()
        assertNotNull(item)
    }

    @Test
    fun test_GetsFirstObjectFromDatabase() = runBlocking {
        database.favouriteDao().insert(user)
        var itemm: GithubUserModel = TestUtil.createDummyUser()
        database.favouriteDao().getAllFavouriteUsers().map {
            itemm = it[0]
        }
        assertEquals(user, itemm)

    }

    @After
    fun tearDown() {
        database.close()
    }
}