package com.suitmedia.test1.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.test1.data.model.response.ListUsers
import com.suitmedia.test1.databinding.ItemUserListBinding

class UserPagingAdapter : PagingDataAdapter<ListUsers, UserPagingAdapter.MyViewHolder>(DIFF_CALLBACK){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyViewHolder(var binding: ItemUserListBinding): RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if(user != null){
            Glide.with(holder.itemView.context)
                .load(user.avatar)
                .into(holder.binding.imgAvatar)
            holder.binding.tvFullname.text = "${user.first_name} ${user.last_name}"
            holder.binding.tvEmail.text = user.email
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListUsers)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListUsers>() {
            override fun areItemsTheSame(oldItem: ListUsers, newItem: ListUsers): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListUsers, newItem: ListUsers): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}