package br.com.android.victorcs.mvvmarvel.data.remote.base.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, func: (T) -> Unit) =
    observe(owner, Observer<T> { obs -> obs?.let { value -> func(value) } })
