package br.com.android.victorcs.mvvmarvel.domain.model

import br.com.android.victorcs.mvvmarvel.data.model.GenericItem

data class EventsDto(
    val available: Int?,
    val collectionURI: String?,
    val items: List<GenericItem>?,
    val returned: Int?
)