package com.chinmay.bloodglucoselogbook

import android.app.Application
import com.chinmay.bloodglucoselogbook.di.appModule
import com.chinmay.bloodglucoselogbook.di.dataBaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, dataBaseModule)
        }
    }
}