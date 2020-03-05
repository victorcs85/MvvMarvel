package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "events",
    foreignKeys = [
        ForeignKey(entity = GenericItem::class, parentColumns = ["genericItemId"],
            childColumns = ["seriesId"], onDelete = ForeignKey.CASCADE)
    ])
data class Series(
    @PrimaryKey(autoGenerate = true)
    val seriesId: Long,
    val genericItemId: Long,
    @ColumnInfo(name = "series_available")
    val available: Int?,
    @ColumnInfo(name = "series_collection_uri")
    val collectionURI: String?,
    @ColumnInfo(name = "series_returned")
    val returned: Int?
)