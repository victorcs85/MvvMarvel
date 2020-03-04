package br.com.android.victorcs.mvvmarvel.data.entity

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)