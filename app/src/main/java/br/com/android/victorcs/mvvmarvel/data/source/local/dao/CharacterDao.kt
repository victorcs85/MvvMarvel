package br.com.android.victorcs.mvvmarvel.data.source.local.dao

import androidx.room.*
import br.com.android.victorcs.mvvmarvel.data.model.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character): Long

    @Delete
    suspend fun deleteCharacter(character: Character): Int

    @Query("SELECT * from Character")
    suspend fun getCharacterList(): MutableList<Character>
}