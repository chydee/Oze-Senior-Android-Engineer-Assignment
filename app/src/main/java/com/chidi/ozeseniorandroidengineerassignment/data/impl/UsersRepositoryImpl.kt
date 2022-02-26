package com.chidi.ozeseniorandroidengineerassignment.data.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.chidi.ozeseniorandroidengineerassignment.data.GithubUsersPagingSource
import com.chidi.ozeseniorandroidengineerassignment.data.GithubUsersRepository
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import io.reactivex.Flowable

class UsersRepositoryImpl(private val pagingSource: GithubUsersPagingSource) : GithubUsersRepository {

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
}