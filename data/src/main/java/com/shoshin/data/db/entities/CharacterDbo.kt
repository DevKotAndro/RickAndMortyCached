package com.shoshin.data.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterDbo(
    @PrimaryKey
	val id: Int,
	val image: String? = null,
	val gender: String? = null,
	val species: String? = null,
	val created: String? = null,
	@Embedded(prefix = "origin_")
	val origin: OriginDbo? = null,
	val name: String? = null,
	@Embedded(prefix = "location_")
	val location: LocationDbo? = null,
	val episode: List<String>? = null,
	val type: String? = null,
	val url: String? = null,
	val status: String? = null
)