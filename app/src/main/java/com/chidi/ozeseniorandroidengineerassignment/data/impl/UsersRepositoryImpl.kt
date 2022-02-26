package com.chidi.ozeseniorandroidengineerassignment.data.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.repository.remote.ApiService
import com.chidi.ozeseniorandroidengineerassignment.repository.remote.GithubUsersPagingSource
import com.chidi.ozeseniorandroidengineerassignment.repository.remote.GithubUsersRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val pagingSource: GithubUsersPagingSource, private val service: ApiService) : GithubUsersRepository {

    override fun getGithubUsers(): Flowable<PagingData<GithubUserModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40
            ),
            pagingSourceFactory = { pagingSource }
        ).flowable
    }

    override fun getUserData(login: String): Observable<Response<GithubUserModel>> {
        return service.getGithubUserData(login)
    }
}