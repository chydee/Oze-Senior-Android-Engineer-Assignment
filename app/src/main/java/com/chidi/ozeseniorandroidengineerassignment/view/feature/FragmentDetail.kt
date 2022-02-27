package com.chidi.ozeseniorandroidengineerassignment.view.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.chidi.ozeseniorandroidengineerassignment.R
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
        binding.lifecycleOwner = this
        fetchData(args.login)
        configureObservers()
        if (binding.user.isFavourite) {
            binding.btnAddToFavourites.text = getString(R.string.already_a_fav_text)
        }
        binding.btnAddToFavourites.setOnClickListener {
            binding.user?.let {
                if (binding.user.isFavourite) {
                    viewModel.insertUser(it.toSqliteConstraintData())
                } else {
                    viewModel.removeUser(it.toSqliteConstraintData())
                }

            }
        }
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
            }
        }

        viewModel.isFavourite.observe(viewLifecycleOwner) {
            if (it) {
                binding.root.showSnackBar(getString(R.string.marked_as_fav))
            } else {
                binding.root.showSnackBar(getString(R.string.an_error_occurred))
            }
        }

        viewModel.isRemovedLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.btnAddToFavourites.text = getString(R.string.add_to_favourite_btn_text)
            } else {
                binding.root.showSnackBar(getString(R.string.an_error_occurred))
            }
        }

    }


}