package br.com.android.victorcs.mvvmarvel.data.model

data class GenericListResponse(
    val eventsId: Long,
    val available: Int?,
    val collectionURI: String?,
    val item: List<GenericItemResponse>?,
    val returned: Int?
)