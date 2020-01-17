package com.bootcamp.magic.Database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class ArrayListConverter {
    var gson = Gson()

    @TypeConverter
    fun fromItemType (type: String?): ArrayList<String>? {

        if (type == null){

            return Collections.list(type)
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(type, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: ArrayList<String>?): String? {
        return gson.toJson(someObjects)
    }
}