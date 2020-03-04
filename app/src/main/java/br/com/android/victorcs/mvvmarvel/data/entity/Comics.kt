package br.com.android.victorcs.mvvmarvel.data.entity

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)