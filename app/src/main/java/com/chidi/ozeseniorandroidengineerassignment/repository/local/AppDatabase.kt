package com.chidi.ozeseniorandroidengineerassignment.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chidi.ozeseniorandroidengineerassignment.data.models.FavouriteUserModel
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.data.models.RemoteKeys
import com.chidi.ozeseniorandroidengineerassignment.repository.local.dao.FavouriteUserDao
import com.chidi.ozeseniorandroidengineerassignment.repository.local.dao.GithubUsersDao
import com.chidi.ozeseniorandroidengineerassignment.repository.local.dao.RemoteKeysDao

@Database(version = 1, entities = [GithubUserModel::class, FavouriteUserModel::class, RemoteKeys::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RemoteKeysDao
    abstract fun favouriteDao(): FavouriteUserDao
    abstract fun githubUsersDao(): GithubUsersDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "Github_User_App_Database")
                .fallbackToDestructiveMigration()
                .build()
    }
}