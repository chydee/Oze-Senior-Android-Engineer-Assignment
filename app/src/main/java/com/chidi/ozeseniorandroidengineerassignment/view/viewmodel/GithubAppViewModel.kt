package com.chidi.ozeseniorandroidengineerassignment.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.chidi.ozeseniorandroidengineerassignment.data.impl.UsersRepositoryImpl
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GithubAppViewModel @Inject constructor(private val source: UsersRepositoryImpl) : ViewModel() {
    private val disposeBag = CompositeDisposable()

    private val _githubUserRequest = MutableLiveData<PagingData<GithubUserModel>>()
    val usersLiveData: LiveData<PagingData<GithubUserModel>>
        get() = _githubUserRequest

    fun fetchGithubUsers() {
        source.getGithubUsers().subscribeOn(Schedulers.io())
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

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
        disposeBag.dispose()
    }
}