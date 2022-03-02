package com.chidi.ozeseniorandroidengineerassignment.view.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.chidi.ozeseniorandroidengineerassignment.R
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.databinding.FragmentDetailBinding
import com.chidi.ozeseniorandroidengineerassignment.view.feature.base.BaseFragment
import com.chidi.ozeseniorandroidengineerassignment.view.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : BaseFragment() {

    private var binding: FragmentDetailBinding by autoCleared()

    private val args: FragmentDetailArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        fetchData(args.login)
        configureObservers()

    }

    private fun fetchData(login: String) {
        binding.progressBar.visible()
        viewModel.getUserDetail(login)
    }

    private fun configureObservers() {

        viewModel.detailLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.user = it
                binding.progressBar.gone()
                configureButton(it)
            }
        }

        viewModel.isFavourite.observe(viewLifecycleOwner) {
            if (it) {
                binding.root.showSnackBar(getString(R.string.marked_as_fav))
            } else {
                binding.root.showSnackBar(getString(R.string.an_error_occurred))
                binding.btnAddToFavourites.isEnabled = true
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.root.showSnackBar(it)
            }
        }

    }

    private fun configureButton(user: GithubUserModel) {
        binding.btnAddToFavourites.setOnClickListener {
            binding.btnAddToFavourites.isEnabled = false
            viewModel.insertUser(user.toSqliteConstraintData())
        }
    }
}

