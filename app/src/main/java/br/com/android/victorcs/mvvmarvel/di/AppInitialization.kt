package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.data.remote.CloudErrorMapper
import org.koin.core.module.Module
import org.koin.dsl.module

class AppInitialization : ModulesInitialization() {
    override fun init(): List<Module> =listOf(
            module {
                single { CloudErrorMapper() }
            }
        )
}