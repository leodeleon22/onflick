package com.leodeleon.onflick

import android.app.Application
import com.leodeleon.onflick.di.appModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}