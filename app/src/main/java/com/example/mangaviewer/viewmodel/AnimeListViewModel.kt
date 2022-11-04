package com.example.mangaviewer.viewmodel

import android.app.IntentService
import android.content.BroadcastReceiver
import android.os.CountDownTimer
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangaviewer.model.AnimeItem
import okhttp3.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class AnimeListViewModel : ViewModel() {
    var aniList: MutableLiveData<ArrayList<AnimeItem>> = MutableLiveData()
    var number:MutableLiveData<Int> = MutableLiveData()
    var mUrl:String = "https://myanimelist.net/topanime.php"
//    var mm : BroadcastReceiver

    fun setUrl(url:String){
        mUrl = url
    }

    fun initData(){
        aniList.value = ArrayList()
        number.value = 0
    }

    fun getAnimeList(failed: () -> Unit, success: () -> Unit,page:Int) {
        val client = OkHttpClient()
        var url = mUrl
        if(page > 0){
            url += "?limit="
            url += page*50
        }
        val request: Request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                failed.invoke()
            }
            override fun onResponse(call: Call, response: Response) {
                var html = response.body()!!.string()
                val doc: Document = Jsoup.parse(html)
                var list = aniList?.value
                val element = doc.getElementsByClass("di-ib clearfix")

                val element2 = doc.getElementsByClass("information di-ib mt4")
                val score = doc.getElementsByClass("js-top-ranking-score-col di-ib al")

                val element3 = doc.getElementsByClass("hoverinfo_trigger fl-l ml12 mr8")

                for (i in element.indices) {
                    var animeItem : AnimeItem = AnimeItem()
                    animeItem.title = element[i].text()
                    animeItem.desc = element2[i].text()
                    animeItem.score = score[i].text()
                    val a = element3[i].html()
                    val doca: Document = Jsoup.parse(a)
                    val el = doca.select("img[data-src]")
                    for (q in el) {
                        Log.e("dddd", q.attr("data-src"))
                        animeItem.cover = q.attr("data-src")
                    }
                    list?.add(animeItem)
                }
                aniList.postValue(list)
                success.invoke()
            }
        })
    }

    fun addOne(){
        if(aniList.value!!.size > 0){
            var list = aniList?.value
            list?.add(0,list[0])
            aniList.postValue(list)
        }

    }

    fun getMangaList(failed: () -> Unit, success: () -> Unit,page:Int) {
        val client = OkHttpClient()
        var url = mUrl
        if(page > 0){
            url += "?limit="
            url += page*50
        }
        val request: Request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                failed.invoke()
            }
            override fun onResponse(call: Call, response: Response) {
                var html = response.body()!!.string()
                val doc: Document = Jsoup.parse(html)
                var list = aniList?.value
                val element = doc.getElementsByClass("manga_h3")

                val element2 = doc.getElementsByClass("information di-ib mt4")
                val score = doc.getElementsByClass("js-top-ranking-score-col di-ib al")
                val element3 = doc.getElementsByClass("hoverinfo_trigger fl-l ml12 mr8")

                for (i in element.indices) {
                    var animeItem : AnimeItem = AnimeItem()
                    animeItem.title = element[i].text()
                    animeItem.desc = element2[i].text()
                    animeItem.score = score[i].text()
                    val a = element3[i].html()
                    val doca: Document = Jsoup.parse(a)
                    val el = doca.select("img[data-src]")
                    for (q in el) {
                        Log.e("dddd", q.attr("data-src"))
                        animeItem.cover = q.attr("data-src")
                    }
                    list?.add(animeItem)
                }
                aniList.postValue(list)
                success.invoke()
            }
        })
    }
}