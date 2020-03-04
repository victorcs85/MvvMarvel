package br.com.android.victorcs.mvvmarvel.data.source.remote

interface BaseRemoteRepository {
    suspend fun getCharacterList()
}