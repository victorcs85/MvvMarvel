package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.BuildConfig
import br.com.android.victorcs.mvvmarvel.data.mappers.CharacterMapper
import br.com.android.victorcs.mvvmarvel.data.model.CharacterResponse
import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import br.com.android.victorcs.mvvmarvel.data.remote.CloudErrorMapper
import br.com.android.victorcs.mvvmarvel.data.remote.base.RetrofitConfig
import br.com.android.victorcs.mvvmarvel.data.repository.CharacterRepositoryImpl
import br.com.android.victorcs.mvvmarvel.domain.mapper.DomainMapper
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository
import br.com.android.victorcs.mvvmarvel.presentation.character.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class AppInitialization : ModulesInitialization() {
    override fun init(): List<Module> =listOf(
            module {
                single { CloudErrorMapper() }
                single { CharacterMapper() }

                single {
                    RetrofitConfig.create(
                        ApiService::class.java,
                        BuildConfig.API_URL_BASE
                    )
                }
                single<ICharacterRepository> { CharacterRepositoryImpl(api = get(), mapper = get()) }
                viewModel { CharactersViewModel(charactersRepository = get()) }
            }
        )
}