package com.bootcamp.magic.App

import android.app.Application
import com.bootcamp.magic.Injection.koinInjector

class MainApp :Application(){
    override fun onCreate() {
        super.onCreate()
        koinInjector()
    }
}