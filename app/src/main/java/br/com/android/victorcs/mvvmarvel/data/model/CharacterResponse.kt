package br.com.android.victorcs.mvvmarvel.data.model

data class CharacterResponse(
    val comics: ComicsResponse,
    val description: String? = "",
    val events: GenericListResponse,
    val id: Int? = 0,
    val modified: String? = "",
    val name: String? = "",
    val resourceURI: String? = "",
    val series: GenericListResponse,
    val stories: GenericListResponse,
    val thumbnail: ThumbnailResponse,
    val urls: List<UrlResponse>
)