package com.example.mangaviewer.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaviewer.R
import com.example.mangaviewer.adapter.AnimeListAdapter
import com.example.mangaviewer.model.AnimeItem
import com.example.mangaviewer.viewmodel.AnimeListViewModel

class AnimeListFragment : AnimeListBaseFragment() {

    var page = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myViewModel?.setUrl("https://myanimelist.net/topanime.php")
        myViewModel!!.getAnimeList({
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
        myViewModel!!.getAnimeList({
        }, {
        },page)
    }


}