package br.com.android.victorcs.mvvmarvel.domain.model

data class Character(
    val comics: Comics?,
    val description: String?,
    val events: GenericList?,
    val id: Int?,
    val modified: String?,
    val name: String?,
    val resourceURI: String?,
    val series: GenericList?,
    val stories: GenericList?,
    val thumbnail: Thumbnail?,
    val urls: List<Url>?
)