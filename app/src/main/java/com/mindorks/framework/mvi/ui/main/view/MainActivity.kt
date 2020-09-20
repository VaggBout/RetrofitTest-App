package com.mindorks.framework.mvi.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.mvi.R
import com.mindorks.framework.mvi.UsersFragment
import com.mindorks.framework.mvi.data.api.ApiHelperImpl
import com.mindorks.framework.mvi.data.api.RetrofitBuilder
import com.mindorks.framework.mvi.data.model.Api_Response
import com.mindorks.framework.mvi.ui.main.Communicator
import com.mindorks.framework.mvi.ui.main.adapter.MainAdapter
import com.mindorks.framework.mvi.ui.main.intent.MainIntent
import com.mindorks.framework.mvi.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvi.ui.main.viewstate.MainState
import com.mindorks.framework.mvi.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

   // private lateinit var comm: Communicator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        buttonFetchUser.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.GONE

                        //renderList(it.user)
//                        users = it.user.data
                        //Toast.makeText(this@MainActivity, it.user.data[0].email, Toast.LENGTH_SHORT).show()
                        val arguments = Bundle()
                        arguments.putParcelable("users", it.user)
                        val usersFragment = UsersFragment()

                        usersFragment.arguments = arguments;

                        supportFragmentManager!!.beginTransaction()
                            .add(R.id.details_fragment, usersFragment, "UserFragment").commit()

                        //comm.passUserData(it.user.data)
                        //test.passUserData(it.user.data)

                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun renderList(users: Api_Response) {
        recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}
