package com.example.composedemo.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ComposeApp : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        private var instance: ComposeApp? = null


        fun getInstance(): ComposeApp? {
            return instance
        }
    }

}