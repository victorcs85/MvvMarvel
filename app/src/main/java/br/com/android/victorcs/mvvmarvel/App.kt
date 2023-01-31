package br.com.android.victorcs.mvvmarvel

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import br.com.android.victorcs.mvvmarvel.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : MultiDexApplication(), LifecycleObserver {

    //region Lifecycle
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        setupKoin()
        setupTimber()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    //endregion

    //region Private
    private fun setupKoin() = startKoin {
        androidContext(this@App)
        modules(
            ModuleInitializer.modules
        )
        androidLogger(Level.DEBUG)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    //endregion
}