package com.chidi.ozeseniorandroidengineerassignment.repository.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import io.reactivex.Completable

@Dao
interface GithubUsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<GithubUserModel>): Completable


    @Query("SELECT * FROM users")
    fun getAllGithubUsers(): PagingSource<Int, GithubUserModel>

    @Query("DELETE FROM users")
    fun deleteAllUsers(): Completable

}