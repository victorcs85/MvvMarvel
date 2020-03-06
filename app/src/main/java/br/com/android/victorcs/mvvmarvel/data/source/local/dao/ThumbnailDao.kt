package br.com.android.victorcs.mvvmarvel.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.android.victorcs.mvvmarvel.data.model.Thumbnail

@Dao
interface ThumbnailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThumbnail(thumbnail: Thumbnail): Long

    @Delete
    suspend fun deleteThumbnail(thumbnail: Thumbnail): Int

}