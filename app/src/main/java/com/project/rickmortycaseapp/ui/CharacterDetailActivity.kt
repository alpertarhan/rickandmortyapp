package com.project.rickmortycaseapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.load
import com.project.rickmortycaseapp.R
import com.project.rickmortycaseapp.adapters.CharacterAdapter
import com.project.rickmortycaseapp.adapters.EpisodeListAdapter
import com.project.rickmortycaseapp.databinding.ActivityCharacterDetailBinding
import com.project.rickmortycaseapp.viewmodel.CharacterViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var myAdapter: EpisodeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()

    }

    private fun initData() {

        //region ->Initialize Data from Intent
        var detailData = intent
        var detailName = detailData.getStringExtra("character_detail_name")
        var detailStatus = detailData.getStringExtra("status_textView")
        var detailSpecies = detailData.getStringExtra("species_textView")
        var detailGender = detailData.getStringExtra("gender_textView")
        val detailImg = detailData.getStringExtra("character_detail_image")
        val detailEpisode = detailData.getStringArrayListExtra("episode_name_list")
        //endregion

        //episode list recyclerview initialize
        initEpisodeRecyclerView(detailEpisode)

        val loading = LoadingIndicator(this)
        loading.loadProgressBar(loading,500)

        //SetData binds from initialized intent
        binding.characterDetailImage.load(detailImg) {
            crossfade(true)
            crossfade(500)
        }
        binding.characterDetailName.text = detailName
        binding.statusTextView.text = detailStatus
        binding.speciesTextView.text = detailSpecies
        binding.genderTextView.text = detailGender

        //Expand-Collapse Button
        binding.expandImageView.setOnClickListener {
            if (binding.episodeListRecyclerView.visibility == View.GONE) {
                binding.episodeListRecyclerView.visibility = View.VISIBLE
                val collapseClick = resources.getDrawable(R.drawable.ic_collapse)
                binding.expandImageView.setImageDrawable(collapseClick)
            } else {
                val expandClick = resources.getDrawable(R.drawable.ic_expand)
                binding.episodeListRecyclerView.visibility = View.GONE
                binding.expandImageView.setImageDrawable(expandClick)
            }
        }

        //finish Activity -> goBack
        binding.clickableTextView.setOnClickListener {
            finish()
        }

    }

    private fun initEpisodeRecyclerView(detailEpisode: ArrayList<String>?) {
        myAdapter = EpisodeListAdapter(detailEpisode)
        binding.episodeListRecyclerView.apply {
            layoutManager =
                StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
            adapter = myAdapter
            setHasFixedSize(true)
        }
    }
}
    //Todo: Filter Episode List

