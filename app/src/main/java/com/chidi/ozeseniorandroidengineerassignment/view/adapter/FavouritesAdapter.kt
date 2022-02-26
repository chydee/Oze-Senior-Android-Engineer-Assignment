package com.chidi.ozeseniorandroidengineerassignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chidi.ozeseniorandroidengineerassignment.data.models.GithubUserModel
import com.chidi.ozeseniorandroidengineerassignment.databinding.ItemGithubUserFavouriteBinding
import com.chidi.ozeseniorandroidengineerassignment.view.adapter.delegate.FavouriteGithubUserDelegate

class FavouritesAdapter(private val listener: FavouriteGithubUserDelegate) : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<GithubUserModel>() {
        override fun areItemsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder(private var binding: ItemGithubUserFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubUserModel) {
            binding.model = item
            with(item) {
                binding.btnDeleteFavourite.setOnClickListener {
                    listener.onItemDeleteClick(this)
                }
            }
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemGithubUserFavouriteBinding = ItemGithubUserFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    internal fun submitData(leaders: List<GithubUserModel>) {
        differ.submitList(leaders)
    }


}