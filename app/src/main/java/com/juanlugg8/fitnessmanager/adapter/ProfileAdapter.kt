package com.juanlugg8.fitnessmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juanlugg8.fitnessmanager.databinding.RowProfileListBinding
import com.juanlugg8.fitnessmanager.entity.Profile

class ProfileAdapter(
    private val onClick: (profile: Profile, nav: Int) -> Unit,
    private val onDelete: (profile: Profile) -> Unit
) : ListAdapter<Profile, ProfileAdapter.ListView>(PROFILE_COMPARATOR) {


    inner class ListView(var binding: RowProfileListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            with(binding) {
                tvId.text = profile.id.toString()
                tvUser.text = profile.user.name
                tvDate.text = profile.dateStarted
                tvHeight.text = profile.height.toString()
                tvWeight.text = profile.weight.toString()
            }
        }
    }

    companion object {
        private val PROFILE_COMPARATOR = object : DiffUtil.ItemCallback<Profile>() {
            override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListView {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListView(RowProfileListBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: ListView, position: Int) {
        val profile = getItem(position)
        holder.bind(profile)
    }
}
