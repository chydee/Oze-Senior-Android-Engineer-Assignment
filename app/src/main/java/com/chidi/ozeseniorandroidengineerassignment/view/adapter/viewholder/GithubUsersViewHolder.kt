package com.chidi.ozeseniorandroidengineerassignment.view.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chidi.ozeseniorandroidengineerassignment.R
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.databinding.ItemGithubUserBinding
import java.util.*

class GithubUsersViewHolder(private val binding: ItemGithubUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: GithubUserModel) {
        with(user) {
            binding.userProfilePicture.load(avatar_url) {
                crossfade(true)
            }
            binding.userName.text = login.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
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
