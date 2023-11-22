package br.com.android.victorcs.mvvmarvel.presentation

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import br.com.android.victorcs.mvvmarvel.R
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.presentation.character.CharactersViewModel
import br.com.android.victorcs.mvvmarvel.presentation.screen.CharactersScreen
import br.com.android.victorcs.mvvmarvel.presentation.screen.ShimmerCharacterList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CHARACTERS) {
        composable(CHARACTERS) {
            val viewModel = hiltViewModel<CharactersViewModel>()
//            val characters = emptyList<Character>()
            val characters = viewModel.characters.value

            Scaffold(
               topBar = {
                   CenterAlignedTopAppBar(title = { stringResource(id = R.string.characters_label) })
               }
            ) { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    if (characters.isEmpty()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .progressSemantics()
                                .size(32.dp),
                        )
                    } else {
                        CharactersScreen(
                            characters,
                            onNavigateToDetail = { characterUrl, characterName ->
                                navController.navigate(
                                    CHARACTER_DETAIL.plus("/$characterUrl/$characterName")
                                )
                            }
                        )
                    }
                }
            }


   /*         Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (characters.isEmpty()) {
                    val strokeWidth = 5.dp

//                    CircularProgressIndicator(
//                        modifier = Modifier.drawBehind {
//                            drawCircle(
//                                Color.Red,
//                                radius = size.width / 2 - strokeWidth.toPx() / 2,
//                                style = Stroke(strokeWidth.toPx())
//                            )
//                        },
//                        color = Color.LightGray,
//                        strokeWidth = strokeWidth
//                    )

//                    CircularProgressIndicator(
//                        modifier = Modifier.width(64.dp),
//                        color = MaterialTheme.colorScheme.surfaceVariant,
//                        strokeWidth = strokeWidth
//                    )

                    CircularProgressIndicator()
                } else {
                    CharactersScreen(
                        characters,
                        onNavigateToDetail = { characterUrl, characterName ->
                            navController.navigate(
                                CHARACTER_DETAIL.plus("/$characterUrl/$characterName")
                            )
                        }
                    )
                }
            }
*/
        }
        composable(
            CHARACTER_DETAIL,
            arguments = listOf(
                navArgument(CHARACTER_URL_KEY) { type = NavType.StringType },
                navArgument(CHARACTER_NAME_KEY) { type = NavType.StringType }
            )
        ) {
//            val viewModel = it.sharedViewModel<CharactersViewModel>(navController)
        }

//        navigation(
//            startDestination = CHARACTERS,
//            route = CHARACTER_DETAIL
//        ) {
//            composable(CHARACTERS) {
//                val viewModel = it.sharedViewModel<CharactersViewModel>(navController)
//                CharactersScreen(
//                    viewModel,
//                    onNavigateToDetail = { characterUrl, characterName ->
//                        navController.navigate(
//                            CHARACTER_DETAIL.plus("/$characterUrl/$characterName")
//                        )
//                    }
//                )
//            }
//            composable(
//                CHARACTER_DETAIL,
//                arguments = listOf(
//                    navArgument(CHARACTER_URL_KEY) { type = NavType.StringType },
//                    navArgument(CHARACTER_NAME_KEY) { type = NavType.StringType }
//                )
//            ) {
//                val viewModel = it.sharedViewModel<CharactersViewModel>(navController)
//            }
//        }
    }

}
