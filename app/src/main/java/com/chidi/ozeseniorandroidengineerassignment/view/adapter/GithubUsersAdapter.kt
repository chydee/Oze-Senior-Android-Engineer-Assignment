package com.chidi.ozeseniorandroidengineerassignment.view.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.delegate.GithubUserItemDelegate
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.viewholder.GithubUsersViewHolder

class GithubUsersAdapter(private val delegate: GithubUserItemDelegate) : PagingDataAdapter<GithubUserModel, GithubUsersViewHolder>(
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
}
