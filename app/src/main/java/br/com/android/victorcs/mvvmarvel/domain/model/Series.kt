package br.com.android.victorcs.mvvmarvel.domain.model

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<GenericItem>,
    val returned: Int
)