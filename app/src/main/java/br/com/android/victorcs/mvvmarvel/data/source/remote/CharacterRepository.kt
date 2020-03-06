package br.com.android.victorcs.mvvmarvel.data.source.remote

import br.com.android.victorcs.mvvmarvel.data.mappers.map
import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import br.com.android.victorcs.mvvmarvel.domain.model.CharacterDto
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository

class CharacterRepository(
    private val api: ApiService
) : ICharacterRepository {

    override suspend fun getCharacterList(): List<CharacterDto> {
        val result = api.getCharacterList().await().dataResponse
        return result?.results?.map { it.map() } ?: listOf()
    }

}