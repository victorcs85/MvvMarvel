package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thumbnail")
data class Thumbnail(
    @PrimaryKey(autoGenerate = true)
    val thumbnailId: Long,
    @ColumnInfo(name = "thumbnail_extension")
    val extension: String?,
    @ColumnInfo(name = "thumbnail_path")
    val path: String?
)