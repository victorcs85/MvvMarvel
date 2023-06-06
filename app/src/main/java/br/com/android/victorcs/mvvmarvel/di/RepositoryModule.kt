package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.data.repository.CharacterRepositoryImpl
import br.com.android.victorcs.mvvmarvel.domain.repository.ICharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideCharacterRepository(repository: CharacterRepositoryImpl): ICharacterRepository
}