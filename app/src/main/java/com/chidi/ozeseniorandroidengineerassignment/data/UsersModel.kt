package com.chidi.ozeseniorandroidengineerassignment.data

data class UsersModel(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<GithubUserModel>
)
