package com.chidi.ozeseniorandroidengineerassignment.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_users")
data class FavouriteUserModel(
    val avatar_url: String,
    val bio: String,
    val blog: String,
    val company: String?,
    val created_at: String,
    val email: String?,
    val events_url: String,
    val following_url: String,
    val followersUrl: String,
    val followers: Int?,
    val following: Int?,
    val gists_url: String,
    val gravatar_id: String,
    val hireable: Boolean?,
    val html_url: String,
    @PrimaryKey
    val id: Int,
    val location: String,
    val login: String,
    val name: String,
    val node_id: String,
    val organizations_url: String,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val twitter_username: String?,
    val type: String,
    val updated_at: String,
    val url: String
)