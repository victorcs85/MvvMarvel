package br.com.android.victorcs.mvvmarvel.data.source.local.converters

import androidx.room.TypeConverter
import br.com.android.victorcs.mvvmarvel.data.model.Comics
import br.com.android.victorcs.mvvmarvel.data.model.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ComicsConverters {
    @TypeConverter
    fun fromComicsJson(stat: List<Comics>): String {
        return Gson().toJson(stat)
    }

    @TypeConverter
    fun toComicsList(jsonImages: String): List<Comics> {
        val notesType = object : TypeToken<List<Comics>>() {}.type
        return Gson().fromJson<List<Comics>>(jsonImages, notesType)
    }
}