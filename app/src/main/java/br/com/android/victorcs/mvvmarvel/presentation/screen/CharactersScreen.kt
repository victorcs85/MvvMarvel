package br.com.android.victorcs.mvvmarvel.presentation.screen

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.model.Comics
import br.com.android.victorcs.mvvmarvel.domain.model.GenericList
import br.com.android.victorcs.mvvmarvel.domain.model.Thumbnail
import br.com.android.victorcs.mvvmarvel.domain.model.Url
import br.com.android.victorcs.mvvmarvel.presentation.CHARACTERS
import br.com.android.victorcs.mvvmarvel.presentation.CHARACTER_DETAIL
import br.com.android.victorcs.mvvmarvel.presentation.EMPTY
import br.com.android.victorcs.mvvmarvel.presentation.character.CharactersViewModel
import br.com.android.victorcs.mvvmarvel.presentation.compose.theme.mvvmTheme
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint

private const val BACKGROUND_CHAR_NAME_COLOR = 0x77000000
private const val Z_INDEX = -1f
private const val ZERO = 0
private const val TWO = 2
private const val FOUR = 4
private const val EIGHT = 8
private const val FOURTEEN = 14
private const val SIXTEEN = 16
private const val THIRTY = 30
private val itemHeight = 180.dp

@Composable
private fun SetUpCharacterItem(character: Character, onNavigateToDetail: (String, String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .clickable {
                onNavigateToDetail.invoke(
                    character.urls?.firstOrNull()?.url.orEmpty(),
                    character.name.orEmpty()
                )
            }
            .shadow(FOUR.dp, RoundedCornerShape(EIGHT.dp)),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = character.thumbnail?.pathMedium.orEmpty(),
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(THIRTY.dp)
                    .background(Color(BACKGROUND_CHAR_NAME_COLOR)),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Text(
                    text = character.name ?: EMPTY,
                    modifier = Modifier
                        .shadow(
                            clip = false,
                            elevation = TWO.dp,
                            ambientColor = Color(ZERO),
                            spotColor = Color(ZERO),
                        )
                        .padding(FOUR.dp)
                        .wrapContentWidth()
                        .zIndex(Z_INDEX),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = FOURTEEN.sp,
                    maxLines = TWO,
                    overflow = TextOverflow.Ellipsis

                )
            }
        }
    }
}

@Composable
fun CharactersScreen(viewModel: CharactersViewModel, onNavigateToDetail: (String, String) -> Unit) {
//    mvvmTheme {
        SetUpGrid(viewModel.characters.value, onNavigateToDetail)
//    }
}

@Preview
@Composable
private fun MockedCharacterGridPreview() {
//    mvvmTheme {
        SetUpGrid(mockCharacters, onNavigateToDetail = { url, name ->} )
//        SetUpGrid(mockCharacters, onNavigateToDetail = { mockCharacters[0].urls?.firstOrNull()?.url.orEmpty(),
//            mockCharacters[0].name.orEmpty() -> Unit} )
//    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SetUpGrid(characters: List<Character>, onNavigateToDetail: (String, String) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(TWO),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(SIXTEEN.dp),
        horizontalArrangement = Arrangement.spacedBy(SIXTEEN.dp),
        verticalItemSpacing = SIXTEEN.dp
    ) {
        items(characters) { item ->
            SetUpCharacterItem(item) { characterUrl, characterName ->
                onNavigateToDetail(characterUrl, characterName)
            }
        }
    }
}

private val mockCharacters = listOf(
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    ),
    Character(
        comics = Comics(
            available = 12,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/comics",
            items = null,
            returned = 12
        ),
        description = null,
        events = GenericList(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/events",
            items = null,
            returned = 1
        ),
        id = 1011334,
        modified = "2014-04-29T14:18:17-0400",
        name = "3-D Man",
        resourceURI = "https://gateway.marvel.com/v1/public/characters/1011334",
        series = GenericList(
            available = 3,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/series",
            items = null,
            returned = 3
        ),
        stories = GenericList(
            available = 21,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1011334/stories",
            items = null,
            returned = 20
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        ),
        urls = listOf(
            Url(
                type = "detail",
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    )
)