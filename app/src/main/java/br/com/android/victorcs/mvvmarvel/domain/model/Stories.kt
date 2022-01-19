package br.com.android.victorcs.mvvmarvel.domain.model

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<GenericItem>,
    val returned: Int
)