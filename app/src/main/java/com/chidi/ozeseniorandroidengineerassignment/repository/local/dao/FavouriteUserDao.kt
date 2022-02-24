package com.chidi.ozeseniorandroidengineerassignment.repository.local.dao

import androidx.room.*
import com.chidi.ozeseniorandroidengineerassignment.data.models.FavouriteUserModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavouriteUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: FavouriteUserModel): Completable

    @Query("SELECT * FROM favouriteusermodel WHERE id = :id")
    fun getFavouriteUser(id: Int): Single<FavouriteUserModel>

    @Query("SELECT * FROM favouriteusermodel")
    fun getAllFavouriteUsers(): Single<FavouriteUserModel>

    @Query("DELETE FROM favouriteusermodel")
    fun clearFavourite(): Completable

    @Delete
    fun removeUserFromFavourite(user: FavouriteUserModel): Completable
}