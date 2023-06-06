package br.com.android.victorcs.mvvmarvel.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    protected fun launch(
        errorBlock: ((Throwable) -> Unit?)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch {
            _loading.postValue(true)
            runCatching { block() }
                .onSuccess { _loading.postValue(false) }
                .onFailure { error ->
                    _loading.postValue(false)
                    if (errorBlock != null) {
                        errorBlock.invoke(error)
                    } else {
                        Timber.e(error)
                    }
                }
        }
}