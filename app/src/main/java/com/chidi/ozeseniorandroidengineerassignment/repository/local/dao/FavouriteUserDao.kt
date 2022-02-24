package com.chidi.ozeseniorandroidengineerassignment.repository.local.dao

import androidx.room.*
import com.chidi.ozeseniorandroidengineerassignment.data.GithubUserModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavouriteUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubUserModel): Completable

    @Query("SELECT * FROM githubusermodel WHERE id =:id")
    fun getFavouriteUser(id: Int): Single<GithubUserModel>

    @Query("SELECT * FROM githubusermodel")
    fun getAllFavouriteUsers(): Single<List<GithubUserModel>>

    @Query("DELETE FROM githubusermodel")
    fun clearFavourite(): Completable

    @Delete
    fun removeUserFromFavourite(user: GithubUserModel): Completable
}