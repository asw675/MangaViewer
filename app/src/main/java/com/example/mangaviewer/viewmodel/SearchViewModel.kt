package com.example.mangaviewer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangaviewer.model.FilterItem
import com.example.mangaviewer.model.OptionItem
import okhttp3.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class SearchViewModel : ViewModel() {

    var mUrl:String = "https://myanimelist.net/anime.php"
    var optionList: MutableLiveData<ArrayList<FilterItem>> = MutableLiveData()
    var startYear:Int = 0
    var endYear:Int = 0
    var startMonth:Int = 0
    var endMonth:Int = 0
    var startDay:Int = 0
    var endDay:Int = 0

    fun initData(){
        optionList.value = ArrayList()
    }

    fun getSearchOption(failed: () -> Unit, success: () -> Unit){
        val client = OkHttpClient()
        val request: Request = Request.Builder().url(mUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("tagtag",e.toString())
                failed.invoke()
            }
            override fun onResponse(call: Call, response: Response) {
                var html = response.body()!!.string()

                var valueList = optionList.value
                val doc: Document = Jsoup.parse(html)
                val list = arrayOf("filterByType","score","status","p","r")
                val titleList = arrayOf("Type","Score","Status","Producer","Rated")

                for(filter in list.indices){
                    if(list[filter] == "p")
                        continue
                    val element = doc.getElementById(list[filter])
                    var filterItem = FilterItem()


                    for(i in element.select("option").indices){
                        var optionItem = OptionItem()
                        optionItem.title = element.select("option")[i].attr("value")

                        optionItem.id = element.select("option")[i].text()
                        filterItem.optionItemList.add(optionItem)
                    }
                    filterItem.title = titleList[filter]
                    valueList?.add(filterItem)
                }

                val contentElement = doc.getElementsByClass("category-wrapper")
                var filterItem = FilterItem()
                for (i in contentElement.indices) {
                    val span = contentElement[i].select("span")

                    for(j in span.indices){
//                        Log.e("tagtag",span[j].text())
//                        Log.e("tagtag",span[j].select("input").attr("value"))
                        var optionItem = OptionItem()
                        optionItem.title = span[j].text()

                        optionItem.id = span[j].select("input").attr("value")
                        filterItem.optionItemList.add(optionItem)
                    }
                }

                filterItem.title = "Content Filter"
                valueList?.add(filterItem)
                optionList?.postValue(valueList)
                success.invoke()
            }
        })
    }

    fun setStartTime(year: Int, month: Int, day: Int){
        this.startYear = year
        this.startMonth = month
        this.startDay = day
    }

    fun setEndTime(year: Int, month: Int, day: Int){
        this.endYear = year
        this.endMonth = month
        this.endDay = day
    }
}