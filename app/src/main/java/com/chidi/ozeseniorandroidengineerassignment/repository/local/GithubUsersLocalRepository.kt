package com.chidi.ozeseniorandroidengineerassignment.repository.local

import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import io.reactivex.Completable
import io.reactivex.Single


interface GithubUsersLocalRepository {
    fun insert(userModel: GithubUserModel): Completable
    fun getFavouriteUser(id: Int): Single<GithubUserModel>
    fun getAllFavouriteUsers(): Single<List<GithubUserModel>>
    fun deleteUser(user: GithubUserModel): Completable
    fun clearFavourite(): Completable
}