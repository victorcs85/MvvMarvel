package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.BuildConfig
import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import br.com.android.victorcs.mvvmarvel.data.remote.base.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): ApiService =
        RetrofitConfig.create(
            ApiService::class.java,
            BuildConfig.API_URL_BASE
        )

}
