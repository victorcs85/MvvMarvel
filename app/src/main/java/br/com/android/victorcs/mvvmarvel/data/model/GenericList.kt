package br.com.android.victorcs.mvvmarvel.data.model

data class GenericList(
    val eventsId: Long,
    val available: Int?,
    val collectionURI: String?,
    val item: List<GenericItem>?,
    val returned: Int?
)