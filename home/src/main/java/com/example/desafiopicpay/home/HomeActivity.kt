package com.example.desafiopicpay.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopicpay.home.data.UserDTO
import com.example.desafiopicpay.network.ui.observeOnSuccess
import kotlinx.android.synthetic.main.activity_home.searchView
import kotlinx.android.synthetic.main.activity_home.userListRecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupViews()
        setupViewModel()
        searchView.setIconifiedByDefault(false)
    }

    private fun setupViewModel() {
        homeViewModel.loadUserList()
            .observeOnSuccess(this, ::onSuccess)
    }

    private fun onSuccess(userList: List<UserDTO>) {
        setupRecyclerView(userList)
    }

    private fun setupRecyclerView(userList: List<UserDTO>) {
        val layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,
            false
        )

        userListRecyclerView.layoutManager = layoutManager

        adapter = UserListAdapter(userList)

        userListRecyclerView.adapter = adapter
    }

    private fun setupViews() {
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }
}
