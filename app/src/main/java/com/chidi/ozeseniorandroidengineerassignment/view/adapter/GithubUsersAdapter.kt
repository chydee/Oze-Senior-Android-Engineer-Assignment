package com.chidi.ozeseniorandroidengineerassignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chidi.ozeseniorandroidengineerassignment.R
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.databinding.ItemGithubUserBinding
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.delegate.GithubUserItemDelegate

class GithubUsersAdapter(private val delegate: GithubUserItemDelegate) : PagingDataAdapter<GithubUserModel, GithubUsersAdapter.GithubUsersViewHolder>(
    COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUsersViewHolder {
        return GithubUsersViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {
        getItem(position)?.let { model ->
            holder.bind(model)
            holder.itemView.setOnClickListener {
                delegate.onItemClick(model)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GithubUserModel>() {
            override fun areItemsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    class GithubUsersViewHolder(private val binding: ItemGithubUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GithubUserModel) {
            with(user) {
                binding.model = user
                binding.executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): GithubUsersViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_github_user, parent, false)

                val binding = ItemGithubUserBinding.bind(view)

                return GithubUsersViewHolder(
                    binding
                )
            }
        }
    }
}
