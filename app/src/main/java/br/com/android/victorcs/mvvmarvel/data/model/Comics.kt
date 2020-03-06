package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.*

@Entity(tableName = "comics")
data class Comics(
    @PrimaryKey(autoGenerate = true)
    val comicsId: Long,
    @ColumnInfo(name = "comics_available")
    val available: Int? = 0,
    @ColumnInfo(name = "comics_collection_uri")
    val collectionURI: String? = "",
    @Embedded
    @ColumnInfo(name = "comics_items")
    val item: GenericItem?,
    @ColumnInfo(name = "comics_returned")
    val returned: Int? = 0
)