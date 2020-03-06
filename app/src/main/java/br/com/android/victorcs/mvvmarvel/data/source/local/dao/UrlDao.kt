package br.com.android.victorcs.mvvmarvel.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.android.victorcs.mvvmarvel.data.model.Url

@Dao
interface UrlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrl(url: Url): Long

    @Delete
    suspend fun deleteUrl(url: Url): Int

}