package com.hyosik.android.uistatesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hyosik.android.uistatesample.adapter.PersonAdapter
import com.hyosik.android.uistatesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    private val adapter : PersonAdapter = PersonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvPerson.adapter = adapter

        initViews()
    }

    private fun initViews() {
        viewModel.fetchPerson()
    }

}