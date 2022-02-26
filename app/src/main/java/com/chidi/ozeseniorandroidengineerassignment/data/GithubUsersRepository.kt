package com.chidi.ozeseniorandroidengineerassignment.data

import androidx.paging.PagingData
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import io.reactivex.Flowable


interface GithubUsersRepository {
    fun getGithubUsers(): Flowable<PagingData<GithubUserModel>>
}