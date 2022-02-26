package com.chidi.ozeseniorandroidengineerassignment.repository.local.dao

import androidx.room.*
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavouriteUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubUserModel): Completable

    @Query("SELECT * FROM favourite_users WHERE id = :id")
    fun getFavouriteUser(id: Int): Single<GithubUserModel>

    @Query("SELECT * FROM favourite_users")
    fun getAllFavouriteUsers(): Single<GithubUserModel>

    @Query("DELETE FROM favourite_users")
    fun clearFavourite(): Completable

    @Delete
    fun removeUserFromFavourite(user: GithubUserModel): Completable
}