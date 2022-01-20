package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.BuildConfig
import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import br.com.android.victorcs.mvvmarvel.data.remote.CloudErrorMapper
import br.com.android.victorcs.mvvmarvel.data.remote.base.RetrofitConfig
import br.com.android.victorcs.mvvmarvel.data.repository.CharacterRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

class AppInitialization : ModulesInitialization() {
    override fun init(): List<Module> =listOf(
            module {
                single {
                    RetrofitConfig.create(
                        ApiService::class.java,
                        BuildConfig.API_URL_BASE
                    )
                }
                single { CloudErrorMapper() }
                single { CharacterRepositoryImpl(api = get(), mapper = get()) }
            }
        )
}