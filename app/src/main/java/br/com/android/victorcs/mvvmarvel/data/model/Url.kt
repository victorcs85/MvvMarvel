package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "url")
data class Url(
    @PrimaryKey(autoGenerate = true)
    val urlId: Long,
    @ColumnInfo(name = "url_type")
    val type: String?,
    @ColumnInfo(name = "url")
    val url: String?
)