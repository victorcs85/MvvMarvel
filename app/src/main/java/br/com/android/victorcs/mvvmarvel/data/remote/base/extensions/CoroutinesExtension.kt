package br.com.android.victorcs.mvvmarvel.data.remote.base.extensions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> async(
    dispatcher: CoroutineDispatcher= Dispatchers.IO,
    func: suspend () -> T
): T = withContext(dispatcher) {
    func.invoke()
}