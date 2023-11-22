package br.com.android.victorcs.mvvmarvel.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    protected fun launch(
        errorBlock: ((Throwable) -> Unit?)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch {
            runCatching { block() }
                .onFailure { error ->
                    if (errorBlock != null) {
                        errorBlock.invoke(error)
                    } else {
                        Timber.e(error)
                    }
                }
        }
}
