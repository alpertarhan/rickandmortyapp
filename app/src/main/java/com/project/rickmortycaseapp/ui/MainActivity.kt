package com.project.rickmortycaseapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.rickmortycaseapp.adapters.CharacterAdapter
import com.project.rickmortycaseapp.databinding.ActivityMainBinding
import com.project.rickmortycaseapp.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //initialize some variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: CharacterAdapter
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loading = LoadingIndicator(this)
        loading.loadProgressBar(loading,1000)

        initRecyclerView()
        initData()

    }

    private fun initData() {

        //paged data
        lifecycleScope.launch {
            characterViewModel.dataList.collect { pagingData ->
                myAdapter.submitData(pagingData)
            }
        }
    }
    private fun initRecyclerView() {
        myAdapter = CharacterAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter = myAdapter
            setHasFixedSize(true)
        }
    }

}