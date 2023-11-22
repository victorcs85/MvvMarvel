package br.com.android.victorcs.mvvmarvel.domain.repository

import br.com.android.victorcs.mvvmarvel.domain.model.Character

interface ICharacterRepository {
    suspend fun getCharacterList(): List<Character>
}
