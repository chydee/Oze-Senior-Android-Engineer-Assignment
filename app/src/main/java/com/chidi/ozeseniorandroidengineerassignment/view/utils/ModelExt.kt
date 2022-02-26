package com.chidi.ozeseniorandroidengineerassignment.view.utils

import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel

fun GithubUserModel.toSqliteConstraintData(): GithubUserModel {
    return GithubUserModel(
        this.avatar_url,
        this.bio ?: "",
        this.blog ?: "",
        this.company ?: "",
        this.created_at ?: "",
        this.email ?: "",
        this.events_url ?: "",
        this.following_url ?: "",
        this.followers_url ?: "",
        this.followers ?: 0,
        this.following ?: 0,
        this.gists_url ?: "",
        this.gravatar_id ?: "",
        this.hireable ?: false,
        this.html_url,
        this.id,
        this.location ?: "",
        this.login,
        this.name ?: "",
        this.node_id,
        this.organizations_url ?: "",
        this.public_gists,
        this.public_repos,
        this.received_events_url ?: "",
        this.repos_url ?: "",
        this.site_admin,
        this.starred_url ?: "",
        this.subscriptions_url ?: "",
        this.twitter_username ?: "",
        this.type ?: "",
        this.updated_at ?: "",
        this.url ?: "",
    )
}