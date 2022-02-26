package com.chidi.ozeseniorandroidengineerassignment.utils

import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel

object TestUtil {
    fun createDummyUser() = GithubUserModel(
        avatar_url = "",
        bio = "",
        blog = "",
        company = "",
        created_at = "",
        email = "",
        events_url = "",
        following_url = "",
        followers_url = "",
        followers = 0,
        following = 0,
        gists_url = "",
        gravatar_id = "",
        hireable = false,
        html_url = "",
        id = 1,
        location = "",
        login = "",
        name = "",
        node_id = "",
        organizations_url = "",
        public_gists = 0,
        public_repos = 0,
        received_events_url = "",
        repos_url = "",
        site_admin = false,
        starred_url = "",
        subscriptions_url = "",
        twitter_username = "",
        type = "",
        updated_at = "",
        url = "",
    )
}