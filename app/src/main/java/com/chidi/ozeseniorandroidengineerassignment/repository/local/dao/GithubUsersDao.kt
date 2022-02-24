package com.chidi.ozeseniorandroidengineerassignment.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GithubUsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<GithubUserModel>): Completable


    @Query("SELECT * FROM githubusermodel")
    fun getAllGithubUsers(): Single<List<GithubUserModel>>

    @Query("DELETE FROM githubusermodel")
    fun deleteAllUsers(): Completable

}