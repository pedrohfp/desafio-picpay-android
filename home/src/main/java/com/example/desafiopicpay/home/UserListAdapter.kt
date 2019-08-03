package com.example.desafiopicpay.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopicpay.home.data.UserDTO
import com.example.desafiopicpay.home.util.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_users_item.view.avatarImageView
import kotlinx.android.synthetic.main.list_users_item.view.nameTextView
import kotlinx.android.synthetic.main.list_users_item.view.nicknameTextView

internal class UserListAdapter(
    private val userList: List<UserDTO>
): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_users_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems(userDTO: UserDTO) {
            itemView.nicknameTextView.text = userDTO.username
            itemView.nameTextView.text = userDTO.name

            Picasso.get()
                .load(userDTO.img)
                .transform(CircleTransform())
                .into(itemView.avatarImageView)
        }
    }
}