package br.com.android.victorcs.mvvmarvel.presentation.screen

import android.content.res.Configuration
import android.graphics.LinearGradient
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.model.Comics
import br.com.android.victorcs.mvvmarvel.domain.model.GenericList
import br.com.android.victorcs.mvvmarvel.domain.model.Thumbnail
import br.com.android.victorcs.mvvmarvel.domain.model.Url
import br.com.android.victorcs.mvvmarvel.presentation.EMPTY
import coil.compose.AsyncImage

private const val BACKGROUND_CHAR_NAME_COLOR = 0x77000000
private const val Z_INDEX = -1f
private const val ZERO = 0
private const val TWO = 2
private const val FOUR = 4
private const val EIGHT = 8
private const val FOURTEEN = 14
private const val SIXTEEN = 16
private const val THIRTY = 30
private const val ANIMATION_TIME = 800
private const val START_OFFSET = 0f
private const val END_OFFSET = 100f
private const val REPEAT_TIMES = 10
private val minSize = 180.dp

@Composable
private fun SetUpCharacterItem(character: Character, onNavigateToDetail: (String, String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(minSize)
            .clickable {
                onNavigateToDetail.invoke(
                    character.urls?.firstOrNull()?.url.orEmpty(),
                    character.name.orEmpty()
                )
            }
            .shadow(FOUR.dp, RoundedCornerShape(EIGHT.dp)),
        verticalArrangement = Arrangement.Bottom
    ) {
        val showShimmer = remember { mutableStateOf(true) }
        Box(contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = character.thumbnail?.pathMedium.orEmpty(),
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value)),
                contentScale = ContentScale.Crop,
                onSuccess = { showShimmer.value = false }
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
fun CharactersScreen(characters: List<Character>, onNavigateToDetail: (String, String) -> Unit) =
    SetUpGrid(characters, onNavigateToDetail)

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun MockedCharacterGridPreview() =
        SetUpGrid(mockCharacters, onNavigateToDetail = { url, name ->} )
//        SetUpGrid(mockCharacters, onNavigateToDetail = { mockCharacters[0].urls?.firstOrNull()?.url.orEmpty(),
//            mockCharacters[0].name.orEmpty() -> Unit} )
//    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SetUpGrid(characters: List<Character>, onNavigateToDetail: (String, String) -> Unit) {
    val gridCells = getStaggeredGridCells()

    LazyVerticalStaggeredGrid(
        columns = gridCells,
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

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun getStaggeredGridCells(): StaggeredGridCells {
    val configuration = LocalConfiguration.current
    val isVertical = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val gridCells = if (isVertical) {
        StaggeredGridCells.Fixed(TWO)
    } else {
        StaggeredGridCells.Adaptive(minSize = minSize)
    }
    return gridCells
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
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
                url = "https://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            ),
            Url(
                type = "comiclink",
                url = "https://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_" +
                        "source=bb3c364debdcbe15b5d9feaad550cbc1"
            )
        )
    )
)


@Composable
fun shimmerBrush(showShimmer: Boolean = true,targetValue:Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(ANIMATION_TIME), repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent,Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShimmerCharacterList() {
    val baseColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
    val highlightColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)

    val brush = Brush.linearGradient(
        colors = listOf(baseColor, highlightColor, baseColor),
        start = Offset(START_OFFSET, START_OFFSET),
        end = Offset(END_OFFSET, END_OFFSET),
        tileMode = TileMode.Mirror
    )

    Column {
        repeat(REPEAT_TIMES) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush)
                )
            }
        }
    }
}