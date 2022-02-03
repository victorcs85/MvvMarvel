package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.data.repository.CharacterRepositoryImpl
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ICharacterRepository> { CharacterRepositoryImpl(api = get(), mapper = get()) }
}