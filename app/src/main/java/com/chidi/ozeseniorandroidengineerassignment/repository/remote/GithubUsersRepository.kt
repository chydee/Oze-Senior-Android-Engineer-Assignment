package com.chidi.ozeseniorandroidengineerassignment.repository.remote

import androidx.paging.PagingData
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response


interface GithubUsersRepository {
    fun getGithubUsers(): Flowable<PagingData<GithubUserModel>>

    fun getUserData(login: String): Observable<Response<GithubUserModel>>
}