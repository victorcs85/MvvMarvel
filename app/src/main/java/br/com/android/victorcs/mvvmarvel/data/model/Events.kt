package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "events_table",
    foreignKeys = [
        ForeignKey(entity = GenericItem::class, parentColumns = ["genericItemId"],
            childColumns = ["eventsId"], onDelete = ForeignKey.CASCADE)
    ])
data class Events(
    @PrimaryKey(autoGenerate = true)
    val eventsId: Long,
    val genericItemId: Long,
    @ColumnInfo(name = "events_available")
    val available: Int?,
    @ColumnInfo(name = "events_collection_uri")
    val collectionURI: String?,
    @ColumnInfo(name = "events_returned")
    val returned: Int?
)