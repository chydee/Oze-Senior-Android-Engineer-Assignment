package com.chidi.ozeseniorandroidengineerassignment.data.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.chidi.ozeseniorandroidengineerassignment.core.constants.AppConstants
import com.chidi.ozeseniorandroidengineerassignment.data.GithubUsersMediator
import com.chidi.ozeseniorandroidengineerassignment.data.GithubUsersRepository
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.repository.local.AppDatabase
import io.reactivex.Flowable
import javax.inject.Inject

class GithubUsersRemoteRepoImpl @Inject constructor(
    private val database: AppDatabase,
    private val remoteMediator: GithubUsersMediator
) : GithubUsersRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getGithubUsers(): Flowable<PagingData<GithubUserModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = AppConstants.DEFAULT_RESULT_LIMIT,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { database.githubUsersDao().getAllGithubUsers() }
        ).flowable
    }
}