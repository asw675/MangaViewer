package com.example.mangaviewer

import android.app.Application
import android.content.Context
import com.didichuxing.doraemonkit.DoKit

class MainApplication : Application() {

    companion object {
        var  _context:Application? = null

        fun getApplicationContext():Context{
            return _context!!
        }

    }

    override fun onCreate() {
        super.onCreate()
        _context = this
    }

}