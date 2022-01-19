package br.com.android.victorcs.mvvmarvel

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : MultiDexApplication(), LifecycleObserver {

    //region Lifecycle
    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    //endregion

    //region Private
    private fun setupKoin() {
        startKoin {
//            modules(
//                ModuleInitializer.modules
//            )
            androidLogger(Level.DEBUG)
            androidContext(this@App)
        }
    }

    private fun setupTimber(){
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    //endregion
}