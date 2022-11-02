package com.example.wizeline

import android.content.Context
import androidx.multidex.MultiDexApplication

class BitsoApplication :  MultiDexApplication() {
    private var context: Context? = null
    init {
        instance = this
    }

    companion object {
        var instance: BitsoApplication? = null
        fun getAppContext(): Context {
            return instance!!.context!!
        }
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}