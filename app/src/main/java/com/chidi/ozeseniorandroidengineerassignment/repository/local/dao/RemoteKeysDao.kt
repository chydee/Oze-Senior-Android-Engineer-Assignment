package com.chidi.ozeseniorandroidengineerassignment.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chidi.ozeseniorandroidengineerassignment.data.models.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remotekeys WHERE id = :id")
    fun remoteKeysGithubUsersId(id: Int): RemoteKeys?

    @Query("DELETE FROM users_remote_keys")
    fun clearRemoteKeys()
}

