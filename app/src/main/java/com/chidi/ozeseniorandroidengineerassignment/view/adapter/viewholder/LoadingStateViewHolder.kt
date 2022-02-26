package com.chidi.ozeseniorandroidengineerassignment.view.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.chidi.ozeseniorandroidengineerassignment.R
import com.chidi.ozeseniorandroidengineerassignment.databinding.ItemLoadingBinding

class LoadingStateViewHolder(
    private val binding: ItemLoadingBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup): LoadingStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)

            val binding = ItemLoadingBinding.bind(view)

            return LoadingStateViewHolder(
                binding
            )
        }
    }
}