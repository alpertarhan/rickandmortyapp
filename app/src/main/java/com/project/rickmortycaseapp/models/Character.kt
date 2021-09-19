package com.project.rickmortycaseapp.models

data class Character(
    val created: String,
    val episode: ArrayList<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)