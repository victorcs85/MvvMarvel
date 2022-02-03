package br.com.android.victorcs.mvvmarvel.data.model

data class GenericListResponse(
    val available: Int?,
    val collectionURI: String?,
    val item: List<GenericItemResponse>?,
    val returned: Int?
)