package com.chidi.ozeseniorandroidengineerassignment.repository.remote

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.chidi.ozeseniorandroidengineerassignment.core.constants.AppConstants.DEFAULT_PAGE_INDEX
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.data.models.UsersModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * provides the data source for paging lib from api calls
 */
class GithubUsersPagingSource @Inject constructor(private val service: ApiService) : RxPagingSource<Int, GithubUserModel>() {

    /**
     * calls api if there is any error getting results then return the [LoadResult.Error]
     * for successful response return the results using [LoadResult.Page] for some reason if the results
     * are empty from service like in case of no more data from api then we can pass [null] to
     * send signal that source has reached the end of list
     */
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, GithubUserModel>> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return service.getGithubUsers(page = page)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUserModel>): Int? {
        return state.anchorPosition
    }

    private fun toLoadResult(data: UsersModel, position: Int): LoadResult<Int, GithubUserModel> {
        return LoadResult.Page(
            data = data.items,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (data.items.isEmpty()) null else position + 1
        )
    }


}