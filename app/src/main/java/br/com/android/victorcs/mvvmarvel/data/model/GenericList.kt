package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.Embedded
import androidx.room.PrimaryKey

data class GenericList(
    @PrimaryKey(autoGenerate = true)
    val eventsId: Long,
    val available: Int?,
    val collectionURI: String?,
    @Embedded
    val item: GenericItem?,
    val returned: Int?
)