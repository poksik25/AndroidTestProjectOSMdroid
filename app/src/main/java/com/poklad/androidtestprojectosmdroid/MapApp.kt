package com.poklad.androidtestprojectosmdroid;

import com.poklad.androidtestprojectosmdroid.di.components.AppComponent
import android.app.Application

class MapApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }

    companion object {
        lateinit var daggerAppComponent: AppComponent
    }
}
