package com.chidi.ozeseniorandroidengineerassignment.core.di

import android.content.Context
import androidx.room.Room
import com.chidi.ozeseniorandroidengineerassignment.repository.local.AppDatabase
import com.chidi.ozeseniorandroidengineerassignment.repository.local.LocalDataSource
import com.chidi.ozeseniorandroidengineerassignment.repository.local.dao.FavouriteUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalInjector {

    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "Github_Users_App.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideFavouritesDao(db: AppDatabase) = db.favouriteDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(favouriteUserDao: FavouriteUserDao) = LocalDataSource(favouriteUserDao)
}
