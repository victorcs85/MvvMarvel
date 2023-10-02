package br.com.android.victorcs.mvvmarvel.presentation.character

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository
import br.com.android.victorcs.mvvmarvel.presentation.base.BaseViewModel
import br.com.android.victorcs.mvvmarvel.presentation.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val CHARACTER_DETAIL = "detail"

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: ICharacterRepository
) : BaseViewModel(), LifecycleObserver {

//    private val _characters = MutableLiveData<List<Character>>()
//    val character: LiveData<List<Character>> = _characters
    val characters: MutableState<List<Character>> = mutableStateOf(listOf())

    fun loadCharacters() =
        launch(
            block = {
//                _characters.postValue(
//                    charactersRepository.getCharacterList()
//                )
                charactersRepository.getCharacterList().also {
                    characters.value = it
                }
            }
        )

    fun getCharacterUrl(character: Character) =
        character.urls?.first {
            it.type?.contains(CHARACTER_DETAIL, ignoreCase = false) ?: false
        }?.url ?: EMPTY
}