package br.com.android.victorcs.mvvmarvel.presentation.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository
import br.com.android.victorcs.mvvmarvel.presentation.base.BaseViewModel
import org.koin.core.component.KoinComponent

class CharactersViewModel(
    private val charactersRepository: ICharacterRepository
) : BaseViewModel(), KoinComponent {

    private val _characters = MutableLiveData<List<Character>>()
    val character: LiveData<List<Character>> = _characters

    fun loadCharacters() {
        launch(
            block = {
                _characters.postValue(charactersRepository.getCharacterList())
            },
            errorBlock = {
                //TODO
            }
        )
    }
}