package br.com.android.victorcs.mvvmarvel.data.source.local.converter

import androidx.room.TypeConverter
import br.com.android.victorcs.mvvmarvel.data.model.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class UrlConverter {

    @TypeConverter
    fun fromUrlList(countryLang: List<UrlConverter>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Url>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toUrlList(countryLangString: String?): List<Url>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Url>?>() {}.type
        return gson.fromJson(countryLangString, type)
    }
}