package com.shoshin.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {
    @TypeConverter
    fun fromList(list: List<String>?): String =
        if(list == null) "" else Gson().toJson(list)

    @TypeConverter
    fun toList(serializedList: String): List<String> =
        Gson().fromJson(serializedList, object: TypeToken<List<String>>() {}.type)
}
