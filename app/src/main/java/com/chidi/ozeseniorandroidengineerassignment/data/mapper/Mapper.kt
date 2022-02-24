package com.chidi.ozeseniorandroidengineerassignment.data.mapper

import com.chidi.ozeseniorandroidengineerassignment.data.models.FavouriteUserModel
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel

fun GithubUserModel.toFavouriteModel(): FavouriteUserModel {
    return FavouriteUserModel(
        this.avatar_url,
        this.bio,
        this.blog,
        this.company,
        this.created_at,
        this.email,
        this.events_url,
        this.following_url,
        this.followers_url,
        this.followers,
        this.following,
        this.gists_url,
        this.gravatar_id,
        this.hireable,
        this.html_url,
        this.id,
        this.location,
        this.login,
        this.name,
        this.node_id,
        this.organizations_url,
        this.public_gists,
        this.public_repos,
        this.received_events_url,
        this.repos_url,
        this.site_admin,
        this.starred_url,
        this.subscriptions_url,
        this.twitter_username,
        this.type,
        this.updated_at,
        this.url
    )
}