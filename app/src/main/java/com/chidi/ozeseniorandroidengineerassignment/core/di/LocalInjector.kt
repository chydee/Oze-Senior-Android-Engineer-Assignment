package com.chidi.ozeseniorandroidengineerassignment.core.di

import android.content.Context
import com.chidi.ozeseniorandroidengineerassignment.repository.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalInjector {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideFavouritesDao(db: AppDatabase) = db.favouriteDao()
}
