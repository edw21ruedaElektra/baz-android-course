package com.example.wizeline

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDexApplication

class BadApplication :  MultiDexApplication() {

    private var context: Context? = null

    init {
        instance = this
    }

    companion object {
        var instance: BadApplication? = null

        fun getAppContext(): Context {
            return instance!!.context!!
        }
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}