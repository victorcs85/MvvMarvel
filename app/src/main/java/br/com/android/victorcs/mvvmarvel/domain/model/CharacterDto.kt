package br.com.android.victorcs.mvvmarvel.domain.model

data class CharacterDto(
    val comics: ComicsDto?,
    val description: String?,
    val events: GenericListDto?,
    val id: Int?,
    val modified: String?,
    val name: String?,
    val resourceURI: String?,
    val series: GenericListDto?,
    val stories: GenericListDto?,
    val thumbnail: ThumbnailDto?,
    val urls: List<UrlDto>?
)