package br.com.android.victorcs.mvvmarvel.data.repository

import br.com.android.victorcs.mvvmarvel.data.mappers.CharacterMapper
import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import br.com.android.victorcs.mvvmarvel.data.remote.base.extensions.async
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository

class CharacterRepositoryImpl(
    private val api: ApiService,
    private val mapper: CharacterMapper
): ICharacterRepository {
    override suspend fun getCharacterList(): List<Character> {
        return async {
            api.getCharacterList().dataResponse?.results?.map { mapper.toDomain(it) } ?: emptyList()
        }
    }
}