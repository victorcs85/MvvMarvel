package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "stories",
    foreignKeys = [
        ForeignKey(entity = GenericItem::class, parentColumns = ["genericItemId"],
            childColumns = ["storiesId"], onDelete = ForeignKey.CASCADE)
    ])
data class Stories(
    @PrimaryKey(autoGenerate = true)
    val storiesId: Long,
    val genericItemId: Long,
    @ColumnInfo(name = "stories_available")
    val available: Int?,
    @ColumnInfo(name = "stories_collection_uri")
    val collectionURI: String?,
    @ColumnInfo(name = "stories_returned")
    val returned: Int?
)