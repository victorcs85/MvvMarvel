package br.com.android.victorcs.mvvmarvel.data.model

data class Series(
    val available: Int?,
    val collectionURI: String?,
    val items: List<GenericItem>?,
    val returned: Int?
) {
    constructor() : this(null, null, null, null)
}