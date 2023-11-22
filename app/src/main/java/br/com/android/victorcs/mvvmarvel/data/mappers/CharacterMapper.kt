package br.com.android.victorcs.mvvmarvel.data.mappers

import br.com.android.victorcs.mvvmarvel.data.extensions.toHttps
import br.com.android.victorcs.mvvmarvel.data.model.CharacterResponse
import br.com.android.victorcs.mvvmarvel.data.model.ComicsResponse
import br.com.android.victorcs.mvvmarvel.data.model.GenericListResponse
import br.com.android.victorcs.mvvmarvel.data.model.ThumbnailResponse
import br.com.android.victorcs.mvvmarvel.data.model.GenericItemResponse
import br.com.android.victorcs.mvvmarvel.data.model.UrlResponse
import br.com.android.victorcs.mvvmarvel.domain.mapper.DomainMapper
import br.com.android.victorcs.mvvmarvel.domain.model.Comics
import br.com.android.victorcs.mvvmarvel.domain.model.GenericList
import br.com.android.victorcs.mvvmarvel.domain.model.Thumbnail
import br.com.android.victorcs.mvvmarvel.domain.model.Url
import br.com.android.victorcs.mvvmarvel.domain.model.GenericItem
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterMapper @Inject constructor() : DomainMapper<CharacterResponse, Character> {
    override fun toDomain(from: CharacterResponse): Character = from.map()

    override fun toDomain(from: List<CharacterResponse>): List<Character> = from.map { toDomain(it) }

    private fun CharacterResponse.map(): Character =
        Character(
            comics = comics.map(),
            description = description,
            events = events.map(),
            id = id,
            modified = modified,
            name = name,
            resourceURI = resourceURI?.toHttps(),
            series = series.map(),
            stories = stories.map(),
            thumbnail = thumbnail.map(),
            urls = urls.mapUrlDto()
        )

    private fun ComicsResponse.map(): Comics = Comics(
        available = available,
        collectionURI = collectionURI?.toHttps(),
        items = item?.mapGenericItemDto(),
        returned = returned
    )

    private fun GenericListResponse.map(): GenericList = GenericList(
        available = available,
        returned = returned,
        items = item?.mapGenericItemDto(),
        collectionURI = collectionURI?.toHttps()
    )

    private fun ThumbnailResponse.map(): Thumbnail = Thumbnail(
        extension = extension,
        path = path?.toHttps()
    )

    private fun List<GenericItemResponse>.mapGenericItemDto(): List<GenericItem> = this.map { generic ->
        GenericItem(
            type = generic.type,
            name = generic.name,
            resourceUri = generic.resourceUri?.toHttps()
        )
    }

    private fun List<UrlResponse>.mapUrlDto(): List<Url> = this.map { mUrl ->
        Url(
            type = mUrl.type,
            url = mUrl.url?.toHttps()
        )
    }
}
