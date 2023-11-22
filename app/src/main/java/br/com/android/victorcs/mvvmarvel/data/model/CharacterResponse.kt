package br.com.android.victorcs.mvvmarvel.data.model

import br.com.android.victorcs.mvvmarvel.data.EMPTY
import br.com.android.victorcs.mvvmarvel.data.ZERO

data class CharacterResponse(
    val comics: ComicsResponse,
    val description: String? = EMPTY,
    val events: GenericListResponse,
    val id: Int? = ZERO,
    val modified: String? = EMPTY,
    val name: String? = EMPTY,
    val resourceURI: String? = EMPTY,
    val series: GenericListResponse,
    val stories: GenericListResponse,
    val thumbnail: ThumbnailResponse,
    val urls: List<UrlResponse>
)
