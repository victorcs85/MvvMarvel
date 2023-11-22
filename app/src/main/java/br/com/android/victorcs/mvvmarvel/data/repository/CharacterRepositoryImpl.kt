package br.com.android.victorcs.mvvmarvel.data.repository

import br.com.android.victorcs.mvvmarvel.data.mappers.CharacterMapper
import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import br.com.android.victorcs.mvvmarvel.data.remote.base.extensions.async
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val mapper: CharacterMapper
) : ICharacterRepository {
    override suspend fun getCharacterList(): List<Character> = withContext(Dispatchers.IO) {
        async {
            api.getCharacterList().data?.results?.map { mapper.toDomain(it) } ?: emptyList()
        }
    }

}
