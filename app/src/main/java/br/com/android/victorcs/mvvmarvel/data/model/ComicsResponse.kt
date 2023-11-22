package br.com.android.victorcs.mvvmarvel.data.model

import br.com.android.victorcs.mvvmarvel.data.EMPTY
import br.com.android.victorcs.mvvmarvel.data.ZERO

data class ComicsResponse(
    val available: Int? = ZERO,
    val collectionURI: String? = EMPTY,
    val item: List<GenericItemResponse>?,
    val returned: Int? = ZERO
)
