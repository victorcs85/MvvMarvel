package br.com.android.victorcs.mvvmarvel.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    protected fun launch(
        errorBlock: ((Throwable) -> Unit),
        block: suspend CoroutineScope.() -> Unit
    ) =
        viewModelScope.launch {
            _loading.postValue(true)
            runCatching { block() }
                .onSuccess { _loading.postValue(false) }
                .onFailure { error ->
                    _loading.postValue(false)
                    errorBlock.invoke(error)
                }
        }
}