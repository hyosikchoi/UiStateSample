package com.hyosik.android.uistatesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyosik.android.uistatesample.adapter.PersonAdapter
import com.hyosik.android.uistatesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    private val adapter : PersonAdapter = PersonAdapter()

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initViews()
    }

    private fun initViews() {
        viewModel.fetchPerson()

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)?.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount?.minus(1)

                // next Page
                if (!binding.rvPerson.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount && viewModel.personDummyKey < 100) {
                       viewModel.fetchPerson()
                }
            }

        }
        binding.rvPerson.addOnScrollListener(scrollListener)
        binding.rvPerson.adapter = adapter

    }

    override fun onDestroy() {
        binding.rvPerson.removeOnScrollListener(scrollListener)
        super.onDestroy()
    }

}