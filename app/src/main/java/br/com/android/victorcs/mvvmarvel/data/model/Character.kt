package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "character",
    foreignKeys = [
        ForeignKey(entity = Comics::class, parentColumns = ["comicsId"],
        childColumns = ["charId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Events::class, parentColumns = ["eventsId"],
        childColumns = ["charId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Series::class, parentColumns = ["seriesId"],
        childColumns = ["charId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Stories::class, parentColumns = ["storiesId"],
        childColumns = ["charId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Url::class, parentColumns = ["urlId"],
        childColumns = ["charId"], onDelete = ForeignKey.CASCADE)
    ])
data class Character(
    @PrimaryKey(autoGenerate = true)
    val charId: Long,
    val comicsId: Long,
    val eventsId: Long,
    val seriesId: Long,
    val storiesId: Long,
    val urlId: Long,
    @ColumnInfo(name = "char_description")
    val description: String? = "",
    @ColumnInfo(name = "char_id")
    val id: Int? = 0,
    @ColumnInfo(name = "char_modified")
    val modified: String? = "",
    @ColumnInfo(name = "char_name")
    val name: String? = "",
    @ColumnInfo(name = "char_resource_uri")
    val resourceURI: String? = ""
)