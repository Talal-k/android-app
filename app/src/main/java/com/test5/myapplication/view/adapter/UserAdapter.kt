package com.test5.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.test5.myapplication.data.model.User
import com.test5.myapplication.databinding.UserAttrbuiteBinding

class UserAdapter(private val itemListener: ItemListener): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    interface ItemListener {
        fun OnClicked(id: Int)
    }

    private val users = ArrayList<User>()

    fun setUsers(users: ArrayList<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: UserAttrbuiteBinding,private val listener: UserAdapter.ItemListener) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener {
        private lateinit var user: User

        init {
            binding.root.setOnClickListener(this)
        }


        fun bind(item: User) {
            this.user = item
            binding.firstname.text = item.first_name
            binding.lastName.text = item.last_name
            binding.email.text = item.email
            Glide.with(binding.root)
                .load(item.avatar)
                .transform(CircleCrop())
                .into(binding.image)
        }

        override fun onClick(v: View?) {
            listener.OnClicked(user.id)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return ViewHolder(UserAttrbuiteBinding.inflate(LayoutInflater.from(parent.context),parent,false),itemListener)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}