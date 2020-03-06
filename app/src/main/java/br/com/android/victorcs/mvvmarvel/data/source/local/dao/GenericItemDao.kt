package br.com.android.victorcs.mvvmarvel.data.source.local.dao

import androidx.room.*
import br.com.android.victorcs.mvvmarvel.data.model.GenericItem
import br.com.android.victorcs.mvvmarvel.data.source.local.converter.GenericItemConverter

@Dao
interface GenericItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenericItem(genericItem: GenericItem): Long

    @Delete
    suspend fun deleteGenericItem(genericItem: GenericItem): Int

    @TypeConverters(GenericItemConverter::class)
    @Query("SELECT * from generic_item")
    suspend fun getGenericItemList(): List<GenericItem>

    @TypeConverters(GenericItemConverter::class)
    suspend fun setGenericItemList(items: List<GenericItem>)
}