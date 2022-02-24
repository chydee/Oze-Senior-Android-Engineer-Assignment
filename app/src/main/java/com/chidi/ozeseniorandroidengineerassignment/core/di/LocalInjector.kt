package com.chidi.ozeseniorandroidengineerassignment.core.di

import android.content.Context
import com.chidi.ozeseniorandroidengineerassignment.repository.local.AppDatabase
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

object LocalInjector {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.favouriteDao()
}