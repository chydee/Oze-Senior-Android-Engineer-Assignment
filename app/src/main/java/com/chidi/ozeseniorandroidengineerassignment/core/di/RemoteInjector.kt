package com.chidi.ozeseniorandroidengineerassignment.core.di

import com.chidi.ozeseniorandroidengineerassignment.BuildConfig
import com.chidi.ozeseniorandroidengineerassignment.data.impl.UsersRepositoryImpl
import com.chidi.ozeseniorandroidengineerassignment.repository.remote.ApiService
import com.chidi.ozeseniorandroidengineerassignment.repository.remote.GithubUsersPagingSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L


/**
 * @see[RemoteInjector] acts as a object provider and creator
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteInjector {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        okHttpLogger: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(okHttpLogger)
            .build()
    }

    @Provides
    fun injectDoggoApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideGithubUsersPagingSource(service: ApiService) = GithubUsersPagingSource(service)


    @Singleton
    @Provides
    fun provideUsersRepositoryImpl(pagingSource: GithubUsersPagingSource, service: ApiService) = UsersRepositoryImpl(pagingSource, service)


}