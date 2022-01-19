package br.com.android.victorcs.mvvmarvel.data.model

data class Comics(
    val comicsId: Long,
    val available: Int? = 0,
    val collectionURI: String? = "",
    val item: List<GenericItem>?,
    val returned: Int? = 0
)