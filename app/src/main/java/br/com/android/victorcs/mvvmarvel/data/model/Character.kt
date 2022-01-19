package br.com.android.victorcs.mvvmarvel.data.model

data class Character(
    val charId: Long,
    val comics: Comics,
    val description: String? = "",
    val events: GenericList,
    val id: Int? = 0,
    val modified: String? = "",
    val name: String? = "",
    val resourceURI: String? = "",
    val series: GenericList,
    val stories: GenericList,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)