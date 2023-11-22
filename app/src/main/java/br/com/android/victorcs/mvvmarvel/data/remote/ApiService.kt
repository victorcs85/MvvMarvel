package br.com.android.victorcs.mvvmarvel.data.remote

import br.com.android.victorcs.mvvmarvel.data.model.BaseApiResponse
import br.com.android.victorcs.mvvmarvel.data.model.CharacterResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v1/public/characters?")
    suspend fun getCharacterList(): BaseApiResponse<CharacterResponse>
}
