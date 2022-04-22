package com.shoshin.domain.entities

data class CharacterDomain(
    val id: Int? = null,
    val image: String? = null,
    val gender: String? = null,
    val species: String? = null,
    val created: String? = null,
    val name: String? = null,
    val episode: List<String>? = null,
    val type: String? = null,
    val url: String? = null,
    val status: String? = null,
    val origin: Origin? = null,
    val location: Location? = null
)