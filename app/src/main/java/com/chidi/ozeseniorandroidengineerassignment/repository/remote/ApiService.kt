package com.chidi.ozeseniorandroidengineerassignment.repository.remote

import com.chidi.ozeseniorandroidengineerassignment.core.constants.AppConstants.DEFAULT_QUERY_LOCATION
import com.chidi.ozeseniorandroidengineerassignment.core.constants.AppConstants.SEARCH_ENDPOINT
import com.chidi.ozeseniorandroidengineerassignment.core.constants.AppConstants.USERS
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.data.models.UsersModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("$SEARCH_ENDPOINT/$USERS")
    fun getGithubUsers(@Query("q") query: String = DEFAULT_QUERY_LOCATION, @Query("page") page: Int): UsersModel

    @GET("$USERS/{login}")
    fun getGithubUserData(@Path("login") login: String): Observable<Response<GithubUserModel>>
}