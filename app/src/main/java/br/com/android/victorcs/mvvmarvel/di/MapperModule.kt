package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.data.mappers.CharacterMapper
import br.com.android.victorcs.mvvmarvel.data.remote.CloudErrorMapper
import org.koin.dsl.module

val mapperModule = module {
    single { CloudErrorMapper() }
    single { CharacterMapper() }
}