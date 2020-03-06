package br.com.android.victorcs.mvvmarvel.data.source.local.dao

import androidx.room.*
import br.com.android.victorcs.mvvmarvel.data.model.Character
import br.com.android.victorcs.mvvmarvel.data.source.local.converter.CharacterConverter

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character): Long

    @Delete
    suspend fun deleteCharacter(character: Character): Int

    @TypeConverters(CharacterConverter::class)
    @Query("SELECT * from character")
    suspend fun getCharacterList(): List<Character>

    @TypeConverters(CharacterConverter::class)
    suspend fun setCharacterList(items: List<Character>)

}