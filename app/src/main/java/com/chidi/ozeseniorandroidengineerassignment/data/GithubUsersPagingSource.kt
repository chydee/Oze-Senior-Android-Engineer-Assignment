package com.chidi.ozeseniorandroidengineerassignment.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chidi.ozeseniorandroidengineerassignment.core.constants.AppConstants.DEFAULT_PAGE_INDEX
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.repository.remote.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * provides the data source for paging lib from api calls
 */
class GithubUsersPagingSource @Inject constructor(private val service: ApiService) : PagingSource<Int, GithubUserModel>() {

    /**
     * calls api if there is any error getting results then return the [LoadResult.Error]
     * for successful response return the results using [LoadResult.Page] for some reason if the results
     * are empty from service like in case of no more data from api then we can pass [null] to
     * send signal that source has reached the end of list
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUserModel> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = service.getGithubUsers(page = params.loadSize)
            LoadResult.Page(
                response.items, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.items.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUserModel>): Int? {
        return state.anchorPosition
    }

}