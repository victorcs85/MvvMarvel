package br.com.android.victorcs.mvvmarvel.data.model

data class ComicsResponse(
    val comicsId: Long,
    val available: Int? = 0,
    val collectionURI: String? = "",
    val item: List<GenericItemResponse>?,
    val returned: Int? = 0
)