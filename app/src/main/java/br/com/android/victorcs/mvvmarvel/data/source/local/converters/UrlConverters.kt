package br.com.android.victorcs.mvvmarvel.data.source.local.converters

import androidx.room.TypeConverter
import br.com.android.victorcs.mvvmarvel.data.model.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UrlConverters {
    @TypeConverter
    fun fromUrlJson(stat: List<Url>): String {
        return Gson().toJson(stat)
    }

    @TypeConverter
    fun toUrlList(jsonImages: String): List<Url> {
        val notesType = object : TypeToken<List<Url>>() {}.type
        return Gson().fromJson<List<Url>>(jsonImages, notesType)
    }
}