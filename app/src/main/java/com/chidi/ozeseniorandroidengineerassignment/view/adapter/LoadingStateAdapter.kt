package com.chidi.ozeseniorandroidengineerassignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chidi.ozeseniorandroidengineerassignment.R
import com.chidi.ozeseniorandroidengineerassignment.databinding.ItemLoadingBinding

class LoadingStateAdapter : LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingStateViewHolder {
        return LoadingStateViewHolder.create(parent)
    }

    class LoadingStateViewHolder(
        private val binding: ItemLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.root.isVisible = loadState is LoadState.Loading
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
}