package br.com.android.victorcs.mvvmarvel.di

import br.com.android.victorcs.mvvmarvel.presentation.character.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharactersViewModel(charactersRepository = get()) }
}