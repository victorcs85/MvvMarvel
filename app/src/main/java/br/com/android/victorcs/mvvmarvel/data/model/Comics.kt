package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "comics",
    foreignKeys = [
        ForeignKey(entity = GenericItem::class, parentColumns = ["genericItemId"],
            childColumns = ["comicsId"], onDelete = ForeignKey.CASCADE)
    ])
data class Comics(
    @PrimaryKey(autoGenerate = true)
    val comicsId: Long,
    val genericItemId: Long,
    @ColumnInfo(name = "comics_available")
    val available: Int? = 0,
    @ColumnInfo(name = "comics_collection_uri")
    val collectionURI: String? = "",
    @ColumnInfo(name = "comics_returned")
    val returned: Int? = 0
)