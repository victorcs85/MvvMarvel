package br.com.android.victorcs.mvvmarvel.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.android.victorcs.mvvmarvel.R
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.model.Comics
import br.com.android.victorcs.mvvmarvel.domain.model.GenericList
import br.com.android.victorcs.mvvmarvel.domain.model.Thumbnail
import br.com.android.victorcs.mvvmarvel.domain.model.Url
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import br.com.android.victorcs.mvvmarvel.presentation.compose.theme.mvvmTheme
import coil.compose.AsyncImage

private const val NUMBER_COLUMNS = 2
const val CHARACTER_URL_KEY = "mvvmarvel_character_url"
const val CHARACTER_NAME_KEY = "mvvmarvel_character_name"

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    //region Views
    private var rvCharacters: RecyclerView? = null
    //endregion

    private val viewModel: CharactersViewModel by viewModels()

//    private val characterAdapter = CharactersAdapter { onClickCharacter(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent { characterGridPreview() }
//        setContent { characterGridScreen(emptyList()) }
    }
//    ) = inflater.inflate(R.layout.fragment_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initComponents()
//        setupViewModel()
        loadCharacters()
    }

    private fun loadCharacters() =
        viewModel.loadCharacters()

//    private fun setupViewModel() =
//        viewModel.character.observe(viewLifecycleOwner) { characters ->
//            characterAdapter.submitList(characters)
//        }

    private fun initComponents() {
        rvCharacters = activity?.findViewById(R.id.rv_characters) as? RecyclerView
        rvCharacters?.apply {
            layoutManager = GridLayoutManager(context, NUMBER_COLUMNS)
//            adapter = characterAdapter
        }
    }

    private fun onClickCharacter(character: Character) {
        val characterUrl = viewModel.getCharacterUrl(character)
        findNavController().run {
            if (currentDestination?.id == R.id.charactersFragment) {
                navigate(
                    R.id.action_departmentListFragment_to_departmentDetailFragment,
                    bundleOf(
                        CHARACTER_URL_KEY to characterUrl,
                        CHARACTER_NAME_KEY to character.name
                    )
                )
            }
        }
    }

    val mockCharacters = listOf(
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


    @Composable
    fun setUpCharacterGrid(
        characters: List<Character>,
        onCharacterClick: (Character) -> Unit
    ) {
        val columns = 2
        LazyColumn {
            items(items = characters.chunked(columns)) { rowItems ->

                val context = LocalContext.current
                val screenWidth =
                    with(LocalDensity.current) { context.resources.displayMetrics.widthPixels }
                val itemWidth = screenWidth / 2

                Row(
                    modifier = Modifier.width(itemWidth.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItems.forEach { character ->
                        setUpCharacterItem(character, onCharacterClick)
                    }
                }
            }
        }
    }

    @Composable
    fun setUpCharacterItem(character: Character, onCharacterClick: (Character) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clickable { onCharacterClick(character) }
                .shadow(4.dp, RoundedCornerShape(8.dp)),
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
                        .height(30.dp)
                        .background(Color(0x77000000)),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Text(
                        text = character.name ?: "",
                        modifier = Modifier.shadow(
                            clip = false,
                            elevation = 2.dp,
                            ambientColor = Color(0),
                            spotColor = Color(0),
                        ).padding(4.dp).wrapContentWidth().zIndex(-1f),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis

                    )
                }
            }
        }
    }

    @Composable
    fun characterGridScreen(characters: List<Character>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = getString(R.string.characters_label)) },
//                    navigationIcon = {
//                        IconButton(onClick = {  }) {
//                            Icon(Icons.Default.ArrowBack, contentDescription = getString(R.string.back_button_description))
//                        }
//                    }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding),
                    content = {
                        setUpCharacterGrid(characters = characters) { character ->
                            onClickCharacter(character)
                        }
                    }
                )
            }
        )
    }

    @Preview
    @Composable
    fun characterGridPreview() {
        mvvmTheme {
            SetUpGrid(viewModel.characters.value)
//            SetUpGrid(mockCharacters)
        }
    }

    @Composable
    private fun SetUpGrid(characters: List<Character>) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp
        ) {
            items(characters) { item ->
                setUpCharacterItem(item) { character ->
                    onClickCharacter(character)
                }
            }
        }
    }
}