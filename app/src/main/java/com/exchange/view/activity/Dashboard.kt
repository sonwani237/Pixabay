package com.exchange.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.exchange.R
import com.exchange.base.BaseActivity
import com.exchange.databinding.DashboardBinding
import com.exchange.view.adapter.PicsAdapter
import com.exchange.viewModel.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Dashboard : BaseActivity<DashboardBinding>() {

    private val dashboardViewModel by viewModel<DashboardViewModel>()

    lateinit var mAdapter: PicsAdapter

    override val layoutRes: Int
        get() = R.layout.dashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.viewModel = dashboardViewModel

        dashboardViewModel.mLoading.observe(this) {
            showLoading(it)
        }

        dashboardViewModel.mError.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }

        dashboardViewModel.mResponse.observe(this) {
            mAdapter = PicsAdapter()
            dataBinding.recycler.adapter = mAdapter
            dataBinding.recycler.layoutManager = GridLayoutManager(this, 2)
            mAdapter.setList(it.hits)
        }
    }

    fun onClickSearch(view: View) {
        if (dashboardViewModel.searchText.get()?.isEmpty() == false) {
            dashboardViewModel.searchText.get()?.let { dashboardViewModel.getSearchPics(it) }
        } else {
            Toast.makeText(this, "Please enter some text....", Toast.LENGTH_LONG).show()
        }
    }


    private fun showLoading(it: Boolean?) {
        if (it == true) {
            Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Done...", Toast.LENGTH_LONG).show()
        }
    }

}