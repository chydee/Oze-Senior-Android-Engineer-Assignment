package com.chidi.ozeseniorandroidengineerassignment.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_remote_keys")
data class RemoteKeys(@PrimaryKey val id: Int, val prevKey: Int?, val nextKey: Int?)
