package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "generic_item")
data class GenericItem(
    @PrimaryKey(autoGenerate = true)
    val genericItemId: Long,
    @SerializedName("resourceURI")
    @ColumnInfo(name = "generic_item_resource_uri")
    val resourceUri: String?,
    @ColumnInfo(name = "generic_item_name")
    val name: String?,
    @ColumnInfo(name = "generic_item_type")
    val type: String?
)