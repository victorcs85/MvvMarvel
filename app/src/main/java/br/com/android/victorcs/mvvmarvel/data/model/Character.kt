package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true)
    val _id: Long,
    @Embedded val comics: Comics?,
    val description: String?,
    @Embedded val events: Events?,
    val id: Int?,
    val modified: String?,
    val name: String?,
    val resourceURI: String?,
    @Embedded val series: Series?,
    @Embedded val stories: Stories?,
    @Embedded val thumbnail: Thumbnail?,
    @Embedded val urls: List<Url>?
)