package br.com.android.victorcs.mvvmarvel.data.source.remote

import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import br.com.android.victorcs.mvvmarvel.domain.model.CharacterDto
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository

class CharacterRepository(
    private val api: ApiService
): ICharacterRepository {

    override suspend fun getCharacterList(): CharacterDto {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}