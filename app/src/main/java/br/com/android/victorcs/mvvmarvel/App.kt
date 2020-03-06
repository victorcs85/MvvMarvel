package br.com.android.victorcs.mvvmarvel

import android.content.Context
import androidx.multidex.MultiDex
import br.com.android.victorcs.mvvmarvel.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    //region Lifecycle
    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    //endregion

    //region Private
    private fun setupTimber(){
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    //endregion
}