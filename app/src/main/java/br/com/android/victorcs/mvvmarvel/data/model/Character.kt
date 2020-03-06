package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val charId: Long,
    @Embedded
    @ColumnInfo(name = "char_comics")
    val comics: Comics?,
    @ColumnInfo(name = "char_description")
    val description: String? = "",
    @Embedded
    @ColumnInfo(name = "char_events")
    val events: GenericList?,
    @ColumnInfo(name = "char_id")
    val id: Int? = 0,
    @ColumnInfo(name = "char_modified")
    val modified: String? = "",
    @ColumnInfo(name = "char_name")
    val name: String? = "",
    @ColumnInfo(name = "char_resource_uri")
    val resourceURI: String? = "",
    @Embedded
    @ColumnInfo(name = "char_series")
    val series: GenericList?,
    @Embedded
    @ColumnInfo(name = "char_stories")
    val stories: GenericList?,
    @Embedded
    @ColumnInfo(name = "char_thumbnail")
    val thumbnail: Thumbnail?,
    @Embedded
    @ColumnInfo(name = "char_urls")
    val urls: List<Url>?
)