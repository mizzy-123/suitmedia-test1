package com.suitmedia.test1.ui.thirdscreen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.suitmedia.test1.data.model.response.ListUsers
import com.suitmedia.test1.databinding.ActivityThirdBinding
import com.suitmedia.test1.ui.adapter.LoadingUserPagingAdapter
import com.suitmedia.test1.ui.adapter.UserPagingAdapter
import com.suitmedia.test1.ui.factory.ViewModelFactory

class ThirdActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var listUser: ArrayList<ListUsers>
    private lateinit var userPagingAdapter: UserPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        setupSwipeRefreshLayout()
        initViewModel()
        showRecyclerList()
        setAction()
    }

    private fun initComponents(){
        listUser = ArrayList()
    }

    private fun showRecyclerList(){
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        userPagingAdapter = UserPagingAdapter()
        binding.rvUser.adapter = userPagingAdapter.withLoadStateFooter(
            footer = LoadingUserPagingAdapter {
                userPagingAdapter.retry()
            }
        )

        userViewModel.story.observe(this@ThirdActivity){
            Log.d("ThirdActivity", "New data received")
            userPagingAdapter.submitData(lifecycle, it)

            binding.swipe.isRefreshing = false
        }

        userPagingAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Error || loadState.append is LoadState.Error) {
                // Ada error saat memuat data
                Log.d("StoryActivity", "Error refresh")
                binding.swipe.isRefreshing = false
            }
        }
    }

    private fun setAction(){
        binding.toolBar.setNavigationOnClickListener {
            finish()
        }

        userPagingAdapter.setOnItemClickCallback(object : UserPagingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListUsers) {
                userViewModel.setUsername("${data.first_name} ${data.last_name}")
                Toast.makeText(this@ThirdActivity, "${data.first_name} ${data.last_name} selected", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipe.setOnRefreshListener(this)
    }

    private fun initViewModel(){
        val factory = ViewModelFactory.getInstance(this@ThirdActivity.application)
        userViewModel = ViewModelProvider(this@ThirdActivity, factory).get(UserViewModel::class.java)
    }

    override fun onRefresh() {
        Log.d("ThirdActivity", "Refreshing data")
        userPagingAdapter.refresh()
    }
}