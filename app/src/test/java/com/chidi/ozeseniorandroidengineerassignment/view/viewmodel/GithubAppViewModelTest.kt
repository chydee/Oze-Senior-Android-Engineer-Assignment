package com.chidi.ozeseniorandroidengineerassignment.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chidi.ozeseniorandroidengineerassignment.core.di.RemoteInjector
import com.chidi.ozeseniorandroidengineerassignment.data.impl.UsersRepositoryImpl
import com.chidi.ozeseniorandroidengineerassignment.repository.local.LocalDataSource
import com.chidi.ozeseniorandroidengineerassignment.repository.remote.GithubUsersRepository
import com.chidi.ozeseniorandroidengineerassignment.utils.TestUtil
import com.chidi.ozeseniorandroidengineerassignment.view.getOrAwaitValue
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@UninstallModules(RemoteInjector::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class GithubAppViewModelTest {

    @Module
    @InstallIn(ActivityRetainedComponent::class)
    class TestRepositoryModule {

        @Provides
        fun localRepository(localRepositoryImpl: LocalDataSource): LocalDataSource {
            return Mockito.mock(LocalDataSource::class.java)
        }

        @Provides
        fun remoteRepository(remote: UsersRepositoryImpl): GithubUsersRepository {
            return Mockito.mock(UsersRepositoryImpl::class.java)
        }
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @BindValue
    @JvmField
    val localRepository: LocalDataSource = Mockito.mock(LocalDataSource::class.java)

    @BindValue
    @JvmField
    val remoteRepository: UsersRepositoryImpl = Mockito.mock(UsersRepositoryImpl::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testGetAllUsersList() {

        val viewModel = GithubAppViewModel(remoteRepository, localRepository)
        viewModel.fetchGithubUsers()
        val value = viewModel.usersLiveData.getOrAwaitValue()
        assertThat(value, Matchers.not(CoreMatchers.nullValue()))
    }

    @Test
    fun testGetUserDetail() {

        val viewModel = GithubAppViewModel(remoteRepository, localRepository)
        val login = "lagos"
        viewModel.getUserDetail(login)
        val value = viewModel.detailLiveData.getOrAwaitValue()
        assertThat(value, Matchers.not(CoreMatchers.nullValue()))
    }

    @Test
    fun testFavouriteUsersList_isNotEmpty() {

        val viewModel = GithubAppViewModel(remoteRepository, localRepository)
        viewModel.getFavouriteUsers()
        val value = viewModel.favouritesLiveData.getOrAwaitValue()
        assert(value.isNotEmpty())
    }

    @Test
    fun testInsertFavouriteUser() {

        val viewModel = GithubAppViewModel(remoteRepository, localRepository)
        val user = TestUtil.createDummyUser()
        viewModel.insertUser(user)
        val value = viewModel.isFavourite.getOrAwaitValue()
        assert(value)
    }

    @Test
    fun testDeleteFavouriteUser() {

        val viewModel = GithubAppViewModel(remoteRepository, localRepository)
        val user = TestUtil.createDummyUser()
        viewModel.removeUser(user)
        val value = viewModel.isRemovedLiveData.getOrAwaitValue()
        assert(value)
    }

    @Test
    fun testClearFavouriteTable() {

        val viewModel = GithubAppViewModel(remoteRepository, localRepository)
        viewModel.clearAllFavourites()
        val value = viewModel.isCleared.getOrAwaitValue()
        assert(value)
    }
}