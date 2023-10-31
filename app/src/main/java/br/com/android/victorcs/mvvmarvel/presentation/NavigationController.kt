package br.com.android.victorcs.mvvmarvel.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import br.com.android.victorcs.mvvmarvel.presentation.character.CharactersViewModel
import br.com.android.victorcs.mvvmarvel.presentation.screen.CharactersScreen

@Composable
fun SetupNavController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CHARACTERS) {
        composable(CHARACTERS) {
            val viewModel = hiltViewModel<CharactersViewModel>()
//            val viewModel = it.sharedViewModel<CharactersViewModel>(navController)
            viewModel.loadCharacters()
            CharactersScreen(
                viewModel,
                onNavigateToDetail = { characterUrl, characterName ->
                    navController.navigate(
                        CHARACTER_DETAIL.plus("/$characterUrl/$characterName")
                    )
                }
            )
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