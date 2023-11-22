package br.com.android.victorcs.mvvmarvel.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import br.com.android.victorcs.mvvmarvel.presentation.compose.theme.mvvmTheme
import dagger.hilt.android.AndroidEntryPoint

const val EMPTY = ""
const val CHARACTER_URL_KEY = "mvvmarvel_character_url"
const val CHARACTER_NAME_KEY = "mvvmarvel_character_name"
const val CHARACTERS = "characters"
const val CHARACTER_DETAIL = "character detail"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            mvvmTheme {
                SetupNavController()
            }
        }
    }
}
