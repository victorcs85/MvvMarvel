package br.com.android.victorcs.mvvmarvel.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.android.victorcs.mvvmarvel.data.model.Comics

@Dao
interface ComicsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(character: Comics): Long

    @Delete
    suspend fun deleteComics(character: Comics): Int

}