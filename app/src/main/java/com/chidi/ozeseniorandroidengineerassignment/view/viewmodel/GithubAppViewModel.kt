package com.chidi.ozeseniorandroidengineerassignment.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.chidi.ozeseniorandroidengineerassignment.data.impl.UsersRepositoryImpl
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.repository.local.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class GithubAppViewModel @Inject constructor(private val source: UsersRepositoryImpl, private val local: LocalDataSource) : ViewModel() {
    private val disposeBag = CompositeDisposable()

    private val schedulers = Schedulers.io()

    private val _githubUserRequest = MutableLiveData<PagingData<GithubUserModel>>()
    val usersLiveData: LiveData<PagingData<GithubUserModel>>
        get() = _githubUserRequest

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchGithubUsers() {
        source.getGithubUsers()
            .cachedIn(viewModelScope)
            .subscribeOn(schedulers)
            .subscribe(
                {
                    _githubUserRequest.postValue(it)
                }, {
                    it.printStackTrace()
                }
            ).let {
                disposeBag.add(it)
            }
    }

    private val _retrieveUserDetail = MutableLiveData<GithubUserModel>()
    val detailLiveData: LiveData<GithubUserModel>
        get() = _retrieveUserDetail

    fun getUserDetail(login: String) {
        source.getUserData(login).subscribeOn(schedulers)
            .subscribe(
                {
                    if (it != null) {
                        if (it.isSuccessful) {
                            _retrieveUserDetail.postValue(it.body())
                        } else {
                            _errorLiveData.postValue(it.errorBody()?.string() ?: "An error has occurred! Try again")
                        }
                    }
                }, {
                    it.printStackTrace()
                }
            )
            .let {
                disposeBag.add(it)
            }
    }

    private val _favouriteUsers = MutableLiveData<List<GithubUserModel>>()
    val favouritesLiveData: LiveData<List<GithubUserModel>>
        get() = _favouriteUsers

    fun getFavouriteUsers() {
        local.getAllFavouriteUsers().subscribeOn(schedulers)
            .subscribe(
                {
                    _favouriteUsers.postValue(it)
                }, {
                    it.printStackTrace()
                }
            )
            .let {
                disposeBag.add(it)
            }
    }

    private val _isRemoved = MutableLiveData<Boolean>()
    val isRemovedLiveData: LiveData<Boolean>
        get() = _isRemoved

    fun removeUser(userModel: GithubUserModel) {
        local.deleteUser(userModel).subscribeOn(schedulers)
            .subscribe(
                {
                    _isRemoved.postValue(true)
                }, {
                    it.printStackTrace()
                    _isRemoved.postValue(false)
                }
            )
            .let {
                disposeBag.add(it)
            }
    }

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite

    fun insertUser(userModel: GithubUserModel) {
        local.insert(userModel).subscribeOn(schedulers)
            .subscribe(
                {
                    _isFavourite.postValue(true)
                }, {
                    it.printStackTrace()
                    _isFavourite.postValue(false)
                }
            )
            .let {
                disposeBag.add(it)
            }
    }

    private val _isCleared = MutableLiveData<Boolean>()
    val isCleared: LiveData<Boolean>
        get() = _isCleared

    fun clearAllFavourites() {
        local.clearFavourite().subscribeOn(schedulers)
            .subscribe(
                {
                    _isCleared.postValue(true)
                }, {
                    it.printStackTrace()
                    _isCleared.postValue(false)
                }
            )
            .let {
                disposeBag.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
        disposeBag.dispose()
    }
}