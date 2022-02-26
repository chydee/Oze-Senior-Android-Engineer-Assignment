package com.chidi.ozeseniorandroidengineerassignment.view.feature

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.chidi.ozeseniorandroidengineerassignment.R
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.databinding.FragmentListBinding
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.GithubUsersAdapter
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.LoadingStateAdapter
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.delegate.GithubUserItemDelegate
import com.chidi.ozeseniorandroidengineerassignment.view.feature.base.BaseFragment
import com.chidi.ozeseniorandroidengineerassignment.view.utils.autoCleared
import com.chidi.ozeseniorandroidengineerassignment.view.utils.gone
import com.chidi.ozeseniorandroidengineerassignment.view.utils.snackBarWithAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : BaseFragment(), GithubUserItemDelegate {

    private var binding: FragmentListBinding by autoCleared()
    private lateinit var adapter: GithubUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        adapter = GithubUsersAdapter(this)
        configureRecyclerView()
        getUsers()
    }

    override fun onItemClick(item: GithubUserModel) {
        findNavController().navigate(FragmentHomeDirections.toFragmentDetail(item.login))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.fragmentFavourites) {
            findNavController().navigate(R.id.to_fragmentFavourites)
            true
        } else {
            super.onOptionsItemSelected(item)
        }

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
                binding.root.snackBarWithAction(getString(R.string.loading_error), getString(R.string.retry_btn_text)) { adapter.retry() }
            }
        }
    }

    private fun getUsers() {
        viewModel.fetchGithubUsers()
        viewModel.usersLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.progressLoader.gone()
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }

    }
}