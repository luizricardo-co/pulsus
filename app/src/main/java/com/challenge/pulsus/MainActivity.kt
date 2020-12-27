package com.challenge.pulsus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.pulsus.adapters.ListAdapter
import com.challenge.pulsus.controllers.MainViewModel
import com.challenge.pulsus.controllers.NetworkWebClient
import com.challenge.pulsus.utils.EXTRA_CATEGORY
import com.challenge.pulsus.utils.EXTRA_DATA
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {

    var listAdapter: ListAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var nameList = ArrayList<String>()
    var currentCategory: String = ""

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAdapter = ListAdapter(nameList, this, this)
        linearLayoutManager = LinearLayoutManager(this)

        categoryList.layoutManager = linearLayoutManager
        categoryList.adapter = listAdapter

        viewModel = ViewModelProvider(this,
            MainViewModel.MainViewModelFactory(NetworkWebClient())
        ).get(MainViewModel::class.java)

        viewModel.categoryLiveData.observe(this, Observer {
            updateList(it)
        })

        viewModel.dataDetail.observe(this, Observer {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY, currentCategory)
                putExtra(EXTRA_DATA, it)
            }
            llProgressBar.visibility = ProgressBar.INVISIBLE
            categoryList.visibility = RecyclerView.VISIBLE
            startActivity(intent)
        })

        viewModel.handlerError.observe(this, Observer {
            llProgressBar.visibility = ProgressBar.INVISIBLE
            categoryList.visibility = RecyclerView.VISIBLE

            Toast.makeText(baseContext,it.message,Toast.LENGTH_SHORT).show()
        })

        viewModel.getCategories()

    }

    private fun updateList(notes: List<String>) {
        val recyclerView = categoryList
        recyclerView.adapter = ListAdapter(notes, this, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.visibility = RecyclerView.VISIBLE
        llProgressBar.visibility = ProgressBar.INVISIBLE
    }

    override fun onItemClick(current: String) {
        llProgressBar.visibility = ProgressBar.VISIBLE
        categoryList.visibility = RecyclerView.INVISIBLE
        currentCategory = current
        viewModel.getCategoryDetail(current);

    }

}


