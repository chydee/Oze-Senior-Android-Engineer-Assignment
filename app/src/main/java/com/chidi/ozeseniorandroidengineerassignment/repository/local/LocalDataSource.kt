package com.chidi.ozeseniorandroidengineerassignment.repository.local

import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.repository.local.dao.FavouriteUserDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val favouriteUserDao: FavouriteUserDao) : GithubUsersLocalRepository {
    override fun insert(userModel: GithubUserModel): Completable {
        return favouriteUserDao.insert(userModel)
    }

    override fun getFavouriteUser(id: Int): Single<GithubUserModel> {
        return favouriteUserDao.getFavouriteUser(id)
    }

    override fun getAllFavouriteUsers(): Single<List<GithubUserModel>> {
        return favouriteUserDao.getAllFavouriteUsers()
    }

    override fun deleteUser(user: GithubUserModel): Completable {
        return favouriteUserDao.deleteUser(user)
    }

    override fun clearFavourite(): Completable {
        return favouriteUserDao.clearFavourite()
    }
}