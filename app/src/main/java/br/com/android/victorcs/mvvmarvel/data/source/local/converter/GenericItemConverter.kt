package br.com.android.victorcs.mvvmarvel.data.source.local.converter

import androidx.room.TypeConverter
import br.com.android.victorcs.mvvmarvel.data.model.GenericItem
import br.com.android.victorcs.mvvmarvel.data.model.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class GenericItemConverter {

    @TypeConverter
    fun fromGenericItemList(countryLang: List<GenericItemConverter>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<GenericItem>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toGenericItemList(countryLangString: String?): List<GenericItem>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<GenericItem>?>() {}.type
        return gson.fromJson(countryLangString, type)
    }
}