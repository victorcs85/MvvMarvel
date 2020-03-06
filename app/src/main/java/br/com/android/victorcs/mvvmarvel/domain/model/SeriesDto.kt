package br.com.android.victorcs.mvvmarvel.domain.model

data class SeriesDto(
    val available: Int?,
    val collectionURI: String?,
    val items: List<GenericItemDto>?,
    val returned: Int?
)