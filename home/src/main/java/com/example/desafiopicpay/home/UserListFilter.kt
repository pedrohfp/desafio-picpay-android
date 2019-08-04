package com.example.desafiopicpay.home

import android.widget.Filter
import com.example.desafiopicpay.home.data.UserDTO

internal class UserListFilter(
    private val userListFull: List<UserDTO>,
    private var userListFiltered: MutableList<UserDTO>,
    private val notifyAdapter: () -> Unit
) : Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        userListFiltered.clear()

        if (constraint!!.trim().isEmpty()) {
            userListFiltered.addAll(userListFull)
        } else {
            userListFull.forEach {
                addItemOnFilteredList(it, constraint)
            }
        }

        return FilterResults().apply {
            values = userListFiltered
        }
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults) {
        notifyAdapter.invoke()
    }

    private fun addItemOnFilteredList(userDTO: UserDTO, constraint: CharSequence) {
        if (userDTO.name.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
            userListFiltered.add(userDTO)
        }
    }

}