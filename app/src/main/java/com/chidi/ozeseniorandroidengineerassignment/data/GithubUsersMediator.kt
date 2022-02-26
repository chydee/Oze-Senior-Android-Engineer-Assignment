package com.chidi.ozeseniorandroidengineerassignment.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.chidi.ozeseniorandroidengineerassignment.core.constants.AppConstants.DEFAULT_PAGE_INDEX
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.data.models.RemoteKeys
import com.chidi.ozeseniorandroidengineerassignment.data.models.UsersModel
import com.chidi.ozeseniorandroidengineerassignment.repository.local.AppDatabase
import com.chidi.ozeseniorandroidengineerassignment.repository.remote.ApiService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.InvalidObjectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GithubUsersMediator @Inject constructor(private val service: ApiService, private val appDatabase: AppDatabase) : RxRemoteMediator<Int, GithubUserModel>() {

    override fun loadSingle(loadType: LoadType, state: PagingState<Int, GithubUserModel>): Single<MediatorResult> {
        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                when (it) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getLastRemoteKey(state)
                            ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                        remoteKeys.nextKey
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getFirstRemoteKey(state)
                            ?: throw InvalidObjectException("Invalid state, key should not be null")
                        //end of list condition reached
                        remoteKeys.prevKey ?: INVALID_PAGE
                    }
                }
            }
            .flatMap { page ->
                if (page == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    service.getGithubUsers(page = page)
                        .map { insertToDb(page, loadType, it) }
                        .map<MediatorResult> { MediatorResult.Success(endOfPaginationReached = it.items.isEmpty()) }
                        .onErrorReturn { MediatorResult.Error(it) }
                }

            }
    }


    /**
     * this returns the page key or the final end of list success result
     */
    fun getKeyPageData(loadType: LoadType, state: PagingState<Int, GithubUserModel>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun insertToDb(page: Int, loadType: LoadType, data: UsersModel): UsersModel {
        appDatabase.beginTransaction()

        try {
            if (loadType == LoadType.REFRESH) {
                appDatabase.getRepoDao().clearRemoteKeys()
                appDatabase.githubUsersDao().deleteAllUsers()
            }

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (data.items.isEmpty()) null else page + 1
            val keys = data.items.map {
                RemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey)
            }
            appDatabase.getRepoDao().insertAll(keys)
            appDatabase.githubUsersDao().insertAll(data.items)
            appDatabase.setTransactionSuccessful()

        } finally {
            appDatabase.endTransaction()
        }

        return data
    }

    /**
     * get the last remote key inserted which had the data
     */
    private fun getLastRemoteKey(state: PagingState<Int, GithubUserModel>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { user -> appDatabase.getRepoDao().remoteKeysGithubUsersId(user.id) }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private fun getFirstRemoteKey(state: PagingState<Int, GithubUserModel>): RemoteKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { user -> appDatabase.getRepoDao().remoteKeysGithubUsersId(user.id) }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, GithubUserModel>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                appDatabase.getRepoDao().remoteKeysGithubUsersId(id)
            }
        }
    }

    companion object {
        const val INVALID_PAGE = -1
    }
}