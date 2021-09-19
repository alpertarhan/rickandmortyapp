package com.project.rickmortycaseapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import coil.load
import com.project.rickmortycaseapp.R
import com.project.rickmortycaseapp.databinding.ActivityCharacterDetailBinding

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initRecyclerView()

        initData()

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initData() {

        //Initialize Data from Intent
        var detailData = intent
        var detailName = detailData.getStringExtra("character_detail_name")
        var detailStatus = detailData.getStringExtra("status_textView")
        var detailSpecies = detailData.getStringExtra("species_textView")
        var detailGender = detailData.getStringExtra("gender_textView")
        val detailImg = detailData.getStringExtra("character_detail_image")
        val detailEpisode = detailData.getStringArrayListExtra("episode_name_list")

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
        binding.episodeViewList.text = detailEpisode?.toString()

        //Expand-Collapse Button
        binding.expandImageView.setOnClickListener {
            if (binding.expandedLayout.visibility == View.GONE) {
                binding.expandedLayout.visibility = View.VISIBLE
                val collapseClick = resources.getDrawable(R.drawable.ic_collapse)
                binding.expandImageView.setImageDrawable(collapseClick)
            } else {
                val expandClick = resources.getDrawable(R.drawable.ic_expand)
                binding.expandedLayout.visibility = View.GONE
                binding.expandImageView.setImageDrawable(expandClick)
            }

            //int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            //
            //Drawable res = getResources().getDrawable(imageResource);
            //imageView.setImageDrawable(res);
        }
        //finish Activity -> goBack
        binding.clickableTextView.setOnClickListener {
            finish()
        }
    }
    //Todo: Filter Episode List

}