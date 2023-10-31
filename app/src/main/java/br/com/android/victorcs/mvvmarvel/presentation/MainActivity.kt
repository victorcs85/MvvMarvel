package br.com.android.victorcs.mvvmarvel.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.android.victorcs.mvvmarvel.presentation.character.CharactersViewModel
import br.com.android.victorcs.mvvmarvel.presentation.compose.theme.mvvmTheme
import dagger.hilt.android.AndroidEntryPoint

const val EMPTY = ""
const val CHARACTER_URL_KEY = "mvvmarvel_character_url"
const val CHARACTER_NAME_KEY = "mvvmarvel_character_name"
const val CHARACTERS = "characters"
const val CHARACTER_DETAIL = "character detail"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //region Views
    private var loadingView: ViewGroup? = null
    private var container: FragmentContainerView? = null
    //endregion

    private val viewModel: CharactersViewModel by viewModels()
//    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mvvmTheme {
                SetupNavController()
            }
        }
        initViewModel()
    }

    private fun initViewModel() = viewModel.loading.observe(this) { isShow ->
        if (isShow) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun showLoading() {
        loadingView?.visibility = View.VISIBLE
        container?.visibility = View.GONE
    }

    private fun hideLoading() {
        loadingView?.visibility = View.GONE
        container?.visibility = View.VISIBLE
    }



}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}