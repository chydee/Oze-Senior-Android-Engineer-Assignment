package com.chidi.ozeseniorandroidengineerassignment.view.adapter.delegate

import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel

interface FavouriteGithubUserDelegate {
    fun onItemClick(item: GithubUserModel)
    fun onItemDeleteClick(item: GithubUserModel)
}