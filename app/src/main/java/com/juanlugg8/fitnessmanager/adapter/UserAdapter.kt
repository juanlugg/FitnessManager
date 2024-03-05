package com.juanlugg8.fitnessmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.juanlugg8.fitnessmanager.databinding.RowUserListBinding
import com.juanlugg8.fitnessmanager.entity.User

class UserAdapter(
    private val onClick: (user: User, nav: Int) -> Unit,
    private val onDelete: (user: User) -> Unit
) : ListAdapter<User, UserAdapter.ListView>(USER_COMPARATOR) {


    inner class ListView(var binding: RowUserListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvId.text = user.id.toString()
                tvName.text = user.name
                tvPhone.text = user.phone
                cvUserList.setOnLongClickListener {
                    Snackbar.make(binding.root, "User ${user.id} deleted", Snackbar.LENGTH_LONG)
                        .show()
                    onDelete(user)
                    true
                }
            }
        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListView {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListView(RowUserListBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ListView, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
}