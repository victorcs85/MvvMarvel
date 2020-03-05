package br.com.android.victorcs.mvvmarvel.data.source.local.converters

import androidx.room.TypeConverter
import br.com.android.victorcs.mvvmarvel.data.model.GenericItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenericItemConverters {
    @TypeConverter
    fun fromGenericItemJson(stat: List<GenericItem>): String {
        return Gson().toJson(stat)
    }

    @TypeConverter
    fun toGenericItemList(jsonImages: String): List<GenericItem> {
        val notesType = object : TypeToken<List<GenericItem>>() {}.type
        return Gson().fromJson<List<GenericItem>>(jsonImages, notesType)
    }
}