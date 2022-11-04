package com.example.mangaviewer.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.ArrayMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mangaviewer.R
import com.example.mangaviewer.SearchActivity
import com.example.mangaviewer.adapter.AnimeListAdapter
import com.example.mangaviewer.model.AnimeItem
import com.example.mangaviewer.util.ToastUtil
import com.example.mangaviewer.viewmodel.AnimeListViewModel

abstract class AnimeListBaseFragment : Fragment() {

    var recyAnime: RecyclerView? = null
    var myViewModel: AnimeListViewModel? = null
    var refreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(AnimeListViewModel::class.java)
        myViewModel!!.initData()
        bindViewAndData()
//        activity.sendOrderedBroadcast()
//        ArrayMap
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_anime, container, false)
        recyAnime = view.findViewById<RecyclerView>(R.id.anime_recy)
//        refreshLayout = view.findViewById(R.id.swipe_refresh)


        recyAnime?.adapter = AnimeListAdapter(context, myViewModel!!.aniList.value)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyAnime?.layoutManager = layoutManager

        recyAnime?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(recyAnime?.canScrollVertically(1) == false)
                    getMoreData()
                super.onScrolled(recyclerView, dx, dy)
            }
        })
//        refreshLayout?.setOnRefreshListener {
//            refreshLayout?.isRefreshing = true
//            Toast.makeText(context,"asd",Toast.LENGTH_LONG).show()
//        }
        view.findViewById<CardView>(R.id.cv_goto_search).setOnClickListener {
            startActivity(Intent(activity,SearchActivity::class.java))
        }
        return view
    }

    private fun bindViewAndData() {
        myViewModel?.aniList?.observe(this, Observer<ArrayList<AnimeItem>> { ani -> //收到回调后更新UI界面
            recyAnime!!.adapter?.notifyDataSetChanged()
        })
    }

    abstract fun getMoreData()

}