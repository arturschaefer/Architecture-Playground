package com.schaefer.architectureplayground.model

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Location,
    val species: String,
    val status: StatusCharacter,
    val type: String,
    val url: String
)