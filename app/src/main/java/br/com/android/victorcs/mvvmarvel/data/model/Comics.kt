package br.com.android.victorcs.mvvmarvel.data.model

import androidx.room.Embedded

data class Comics(
    val available: Int?,
    val collectionURI: String?,
    @Embedded val items: List<GenericItem>?,
    val returned: Int?
) {
    constructor() : this(null, null, null, null)
}