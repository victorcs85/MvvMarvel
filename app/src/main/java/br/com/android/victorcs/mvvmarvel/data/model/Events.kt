package br.com.android.victorcs.mvvmarvel.data.model

data class Events(
    val available: Int?,
    val collectionURI: String?,
    val items: List<GenericItem>?,
    val returned: Int?
) {
    constructor() : this(null, null, null, null)
}