package com.example.mangaviewer.util

import android.app.Application
import android.content.Context
import android.os.Looper
import android.widget.Toast
import com.example.mangaviewer.MainApplication

class ToastUtil {

    companion object {

        fun show(text:String){
            try{
                Toast.makeText(MainApplication.getApplicationContext(),text,Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                Looper.prepare()
                Toast.makeText(MainApplication.getApplicationContext(),text,Toast.LENGTH_SHORT).show()
                Looper.loop()
            }
        }

    }

}