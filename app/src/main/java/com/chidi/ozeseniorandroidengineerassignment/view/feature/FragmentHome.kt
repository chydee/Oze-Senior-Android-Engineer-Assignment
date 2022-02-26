package com.chidi.ozeseniorandroidengineerassignment.view.feature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.chidi.ozeseniorandroidengineerassignment.databinding.FragmentHomeBinding
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.GithubUsersAdapter
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.LoadingStateAdapter
import com.chidi.ozeseniorandroidengineerassignment.view.feature.base.BaseFragment
import com.chidi.ozeseniorandroidengineerassignment.view.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : BaseFragment() {


    private var binding: FragmentHomeBinding by autoCleared()
    private lateinit var adapter: GithubUsersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GithubUsersAdapter()
        configureRecyclerView()
        getUsers()
    }

    private fun configureRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter()
        )
        adapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                Log.d("LoadState:Error", it.error.localizedMessage ?: "Error loading data: RETRY")
                //adapter.retry()
            }
        }
    }

    private fun getUsers() {
        viewModel.fetchGithubUsers()
        viewModel.usersLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.submitData(lifecycle, it)
            }
        }

    }
}