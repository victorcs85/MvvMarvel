package br.com.android.victorcs.mvvmarvel.domain.model

data class GenericListDto(
    val available: Int?,
    val collectionURI: String?,
    val items: List<GenericItemDto>?,
    val returned: Int?
)