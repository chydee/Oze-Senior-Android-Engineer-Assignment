package com.chidi.ozeseniorandroidengineerassignment.view.adapter.delegate

import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel

interface GithubUserItemDelegate {
    fun onItemClick(item: GithubUserModel)
}