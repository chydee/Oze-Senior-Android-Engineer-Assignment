package com.chidi.ozeseniorandroidengineerassignment.view.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.chidi.ozeseniorandroidengineerassignment.R
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.databinding.ItemGithubUserBinding

class GithubUsersViewHolder(private val binding: ItemGithubUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: GithubUserModel) {
        with(user) {
            binding.userProfilePicture.load(user.avatar_url) {
                crossfade(true)
            }
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
