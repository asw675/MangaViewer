package com.example.mangaviewer.thread

import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class NetworkThread: Thread() {
    override fun run() {
        super.run()
        Looper.prepare()
        val handler : Handler = Handler(Looper.myLooper()!!)
        val okHttpClient: OkHttpClient = OkHttpClient()
        val request: Request = Request.Builder().url("https://myanimelist.net/topanime.php").build()
        val runnable = Runnable {
            var call = okHttpClient.newCall(request)
            var response = call.execute()
            var html = response.body()!!.string()
            Log.e("aaaa",html)
            val doc: Document = Jsoup.parse(html)
            val element = doc.getElementsByClass("di-ib clearfix")
            for(e in element){
                Log.e("bbbb",e.text())
            }
            val element2 = doc.getElementsByClass("information di-ib mt4")
            for(e in element2){
                Log.e("cccc",e.text())
            }
            val element3 = doc.getElementsByClass("hoverinfo_trigger fl-l ml12 mr8")
            for(e in element3){
                val a = e.html()
                val doca: Document = Jsoup.parse(a)
                val el = doca.select("img[data-src]")
                for(q in el){
                    Log.e("dddd",q.attr("data-src"))
                }


            }
        }
        handler.post(runnable)
        Looper.loop();
    }
}