package com.chidi.ozeseniorandroidengineerassignment.view.feature

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chidi.ozeseniorandroidengineerassignment.R
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.databinding.FragmentListBinding
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.FavouritesAdapter
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.delegate.FavouriteGithubUserDelegate
import com.chidi.ozeseniorandroidengineerassignment.view.feature.base.BaseFragment
import com.chidi.ozeseniorandroidengineerassignment.view.utils.autoCleared
import com.chidi.ozeseniorandroidengineerassignment.view.utils.gone
import com.chidi.ozeseniorandroidengineerassignment.view.utils.showSnackBar
import com.chidi.ozeseniorandroidengineerassignment.view.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavourites : BaseFragment(), FavouriteGithubUserDelegate {

    private var binding: FragmentListBinding by autoCleared()

    private lateinit var adapter: FavouritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        adapter = FavouritesAdapter(this)
        configureRecyclerView()
        fetchFavourites()
        observeLiveData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.favourte_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.clearAll) {
            clearAll()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    }

    override fun onItemClick(item: GithubUserModel) {
        findNavController().navigate(FragmentFavouritesDirections.favouritesToFragmentDetail(item.login))
    }

    override fun onItemDeleteClick(item: GithubUserModel) {
        viewModel.removeUser(item)
    }

    private fun configureRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter
    }

    private fun fetchFavourites() {
        Log.d("User", "Fetching users")
        viewModel.getFavouriteUsers()
        viewModel.favouritesLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.isNotEmpty()) {
                    adapter.submitData(it)
                    binding.progressLoader.gone()
                } else {
                    binding.recyclerView.gone()
                    binding.emptyFavouriteState.visible()
                }
            }
        }
    }

    private fun clearAll() {
        viewModel.clearAllFavourites()
    }

    private fun observeLiveData() {
        viewModel.isRemovedLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.root.showSnackBar(getString(R.string.fav_user_removed))
                fetchFavourites()
            } else {
                binding.root.showSnackBar(getString(R.string.an_error_occurred))
            }
        }

        viewModel.isCleared.observe(viewLifecycleOwner) {
            if (it) {
                binding.recyclerView.gone()
                binding.emptyFavouriteState.visible()
            } else {
                binding.recyclerView.visible()
                binding.emptyFavouriteState.gone()
            }
        }
    }
}