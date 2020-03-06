package br.com.android.victorcs.mvvmarvel.data.source.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import br.com.android.victorcs.mvvmarvel.data.model.Character

class CharacterConverter {

    @TypeConverter
    fun fromCharacterList(countryLang: List<CharacterConverter>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Character>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCharacterList(countryLangString: String?): List<Character>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Character>?>() {}.type
        return gson.fromJson(countryLangString, type)
    }
}