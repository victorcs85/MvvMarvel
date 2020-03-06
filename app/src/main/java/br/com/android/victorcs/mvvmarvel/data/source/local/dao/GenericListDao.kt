package br.com.android.victorcs.mvvmarvel.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.android.victorcs.mvvmarvel.data.model.GenericList

@Dao
interface GenericListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenericList(genericList: GenericList): Long

    @Delete
    suspend fun deleteGenericList(genericList: GenericList): Int

}