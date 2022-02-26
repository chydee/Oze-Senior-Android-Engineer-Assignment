package com.chidi.ozeseniorandroidengineerassignment.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.repository.local.dao.FavouriteUserDao

@Database(version = 1, entities = [GithubUserModel::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteUserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "Oze_Github_User_Application.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}