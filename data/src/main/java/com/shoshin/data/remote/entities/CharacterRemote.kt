package com.shoshin.data.remote.entities

data class CharacterRemote(
	val id: Int,
	val image: String? = null,
	val gender: String? = null,
	val species: String? = null,
	val created: String? = null,
	val origin: OriginRemote? = null,
	val name: String? = null,
	val location: LocationRemote? = null,
	val episode: List<String>? = null,
	val type: String? = null,
	val url: String? = null,
	val status: String? = null
)




