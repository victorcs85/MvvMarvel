package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.BuildConfig
import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import br.com.android.victorcs.mvvmarvel.data.remote.base.RetrofitConfig
import org.koin.dsl.module

val apiModule = module {
    single {
        RetrofitConfig.create(
            ApiService::class.java,
            BuildConfig.API_URL_BASE
        )
    }
}