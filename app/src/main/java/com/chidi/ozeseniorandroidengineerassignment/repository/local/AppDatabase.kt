package com.chidi.ozeseniorandroidengineerassignment.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.repository.local.dao.FavouriteUserDao

@Database(version = 1, entities = [GithubUserModel::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteUserDao
}