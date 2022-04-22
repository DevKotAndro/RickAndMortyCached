package com.shoshin.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyDbo(
    @PrimaryKey val characterId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)