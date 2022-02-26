package com.chidi.ozeseniorandroidengineerassignment.view.feature.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chidi.ozeseniorandroidengineerassignment.view.viewmodel.GithubAppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    val viewModel: GithubAppViewModel by viewModels()

}