package com.example.desafiopicpay.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopicpay.home.data.UserDTO
import com.example.desafiopicpay.network.ui.ErrorData
import com.example.desafiopicpay.network.ui.observeOnError
import com.example.desafiopicpay.network.ui.observeOnSuccess
import kotlinx.android.synthetic.main.activity_home.userListRecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupViewModel()
    }

    private fun setupViewModel() {
        homeViewModel.loadUserList()
            .observeOnSuccess(this, ::onSuccess)
            .observeOnError(this, ::onError)
    }

    private fun onSuccess(userList: List<UserDTO>) {
        setupRecyclerView(userList)
    }

    private fun onError(errorData: ErrorData) {

    }

    private fun setupRecyclerView(userList: List<UserDTO>) {
        val layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,
            false
        )

        userListRecyclerView.layoutManager = layoutManager

        userListRecyclerView.adapter = UserListAdapter(userList)
    }
}
