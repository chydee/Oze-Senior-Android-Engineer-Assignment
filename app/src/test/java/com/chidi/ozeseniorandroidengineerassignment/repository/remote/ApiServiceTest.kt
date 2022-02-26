package com.chidi.ozeseniorandroidengineerassignment.repository.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chidi.ozeseniorandroidengineerassignment.core.constants.AppConstants.DEFAULT_PAGE_INDEX
import com.chidi.ozeseniorandroidengineerassignment.data.models.UsersModel
import com.chidi.ozeseniorandroidengineerassignment.repository.ApiAbstract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ApiServiceTest : ApiAbstract<ApiService>() {

    @get: Rule
    val disposeRule = CompositeDisposable()

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ApiService

    @Before
    fun initService() {
        service = createService(ApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun test_getCharactersFromNetwork() = runBlocking {
        enqueueResponse("/Users.json")
        val response = (service.getGithubUsers(page = DEFAULT_PAGE_INDEX))
        var responseBody: UsersModel? = null
        response.subscribeOn(Schedulers.io()).subscribe({ responseBody = it }, { responseBody = null }).let { disposeRule.add(it) }
        mockWebServer.takeRequest()

        val loaded = responseBody?.items?.get(0)
        MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(loaded?.name, CoreMatchers.`is`(""))
        MatcherAssert.assertThat(loaded?.avatar_url, CoreMatchers.`is`(""))
        MatcherAssert.assertThat(loaded?.gravatar_id, CoreMatchers.`is`(""))
        MatcherAssert.assertThat(loaded?.type, CoreMatchers.`is`(""))
        MatcherAssert.assertThat(loaded?.followers, CoreMatchers.`is`(0))
        MatcherAssert.assertThat(loaded?.following, CoreMatchers.`is`(0))
        MatcherAssert.assertThat(loaded?.url, CoreMatchers.`is`(""))
        MatcherAssert.assertThat(loaded?.created_at, CoreMatchers.`is`(""))
    }
}