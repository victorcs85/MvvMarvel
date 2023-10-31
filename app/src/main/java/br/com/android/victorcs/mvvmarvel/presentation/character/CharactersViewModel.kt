package br.com.android.victorcs.mvvmarvel.presentation.character

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleObserver
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository
import br.com.android.victorcs.mvvmarvel.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: ICharacterRepository
) : BaseViewModel(), LifecycleObserver {

    val characters: MutableState<List<Character>> = mutableStateOf(listOf())

    fun loadCharacters() =
        launch(
            block = {
                charactersRepository.getCharacterList().also {
                    characters.value = it
                }
            }
        )
}