package br.com.android.victorcs.mvvmarvel.data.mappers

import br.com.android.victorcs.mvvmarvel.data.model.*
import br.com.android.victorcs.mvvmarvel.domain.model.*

fun Character.map(): CharacterDto = CharacterDto(
    comics = comics.map(),
    description = description,
    events = events.map(),
    id = id,
    modified = modified,
    name = name,
    resourceURI = resourceURI,
    series = series.map(),
    stories = stories.map(),
    thumbnail = thumbnail.map(),
    urls = urls.mapUrlDto()
)

fun Comics.map(): ComicsDto = ComicsDto(
    available = available,
    collectionURI = collectionURI,
    items = item?.mapGenericItemDto(),
    returned = returned
)

fun GenericList.map(): GenericListDto = GenericListDto(
    available = available,
    returned = returned,
    items = item?.mapGenericItemDto(),
    collectionURI = collectionURI
)

fun Thumbnail.map(): ThumbnailDto = ThumbnailDto(
    extension = extension,
    path = path
)

fun List<GenericItem>.mapGenericItemDto(): List<GenericItemDto> = this.map { generic ->
    GenericItemDto(
        type = generic.type,
        name = generic.name,
        resourceUri = generic.resourceUri
    )
}

fun List<Url>.mapUrlDto(): List<UrlDto> = this.map { mUrl ->
    UrlDto(
        type = mUrl.type,
        url = mUrl.url
    )
}