package br.com.android.victorcs.mvvmarvel.data.source.remote

import br.com.android.victorcs.mvvmarvel.data.remote.ApiService

class RemoteRepository(
    private val api: ApiService
): BaseRemoteRepository {

    override suspend fun getCharacterList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}