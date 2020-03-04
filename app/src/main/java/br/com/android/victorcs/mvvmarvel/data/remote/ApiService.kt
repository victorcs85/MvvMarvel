package br.com.android.victorcs.mvvmarvel.data.remote

import br.com.android.victorcs.mvvmarvel.data.model.BaseApiResponse
import br.com.android.victorcs.mvvmarvel.data.model.Character
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("characters")
    fun getCharacterList(): Deferred<BaseApiResponse<Character>>
}