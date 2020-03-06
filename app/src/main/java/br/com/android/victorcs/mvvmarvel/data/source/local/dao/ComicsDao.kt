package br.com.android.victorcs.mvvmarvel.data.source.local.dao

import androidx.room.*
import br.com.android.victorcs.mvvmarvel.data.model.Comics
import br.com.android.victorcs.mvvmarvel.data.source.local.converter.CharacterConverter

@Dao
interface ComicsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(character: Comics): Long

    @Delete
    suspend fun deleteComics(character: Comics): Int

    @TypeConverters(CharacterConverter::class)
    @Query("SELECT * from comics")
    suspend fun getComicsList(): MutableList<Comics>

}