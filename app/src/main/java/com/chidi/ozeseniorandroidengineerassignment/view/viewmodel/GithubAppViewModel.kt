package com.chidi.ozeseniorandroidengineerassignment.view.viewmodel

import androidx.lifecycle.ViewModel
import com.chidi.ozeseniorandroidengineerassignment.data.GithubUsersRepository
import javax.inject.Inject

class GithubAppViewModel @Inject constructor(private val repository: GithubUsersRepository) : ViewModel()