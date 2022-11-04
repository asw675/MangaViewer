package com.example.mangaviewer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MangaListFragment : AnimeListBaseFragment() {

    var page = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myViewModel?.setUrl("https://myanimelist.net/topmanga.php")
        myViewModel!!.getMangaList({
        }, {
        },page)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getMoreData() {
        page++
        myViewModel!!.getMangaList({
        }, {
        },page)
    }

}