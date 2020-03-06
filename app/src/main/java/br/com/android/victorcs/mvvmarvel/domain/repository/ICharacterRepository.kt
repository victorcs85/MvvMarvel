package br.com.android.victorcs.mvvmarvel.domain.repository

import br.com.android.victorcs.mvvmarvel.domain.model.CharacterDto

interface ICharacterRepository {
    suspend fun getCharacterList(): List<CharacterDto>
}