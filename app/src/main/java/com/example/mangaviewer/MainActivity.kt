package com.example.mangaviewer

import android.R.id
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.mangaviewer.adapter.MainViewPagerAdapter
import com.example.mangaviewer.fragment.AnimeListFragment
import com.example.mangaviewer.fragment.MangaListFragment
import com.example.mangaviewer.viewmodel.AnimeListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.annotation.NonNull

import android.R.id.tabs
import android.os.Handler
import android.view.Window
import com.example.mangaviewer.fragment.DiscoverFragment
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy


class MainActivity : AppCompatActivity() {

    private var myViewModel: AnimeListViewModel? = null
    private var mainTabLayout: TabLayout? = null
    private var mainViewPager : ViewPager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainViewPager = findViewById(R.id.main_viewpager)
        mainTabLayout = findViewById(R.id.main_tablayout)
        var animeListFragment = AnimeListFragment()
        var mangaListFragment = MangaListFragment()
        var discoverFragment = DiscoverFragment()
        var fragmentList = ArrayList<Fragment>()
        var titleList = ArrayList<String>()
        titleList.add("Anime")
        titleList.add("Manga")
        titleList.add("Discover")
//        titleList.add("Doujinshi")
        fragmentList.add(animeListFragment)
        fragmentList.add(mangaListFragment)
        fragmentList.add(discoverFragment)
//        fragmentList.add(animeListFragment)
        mainViewPager?.adapter = MainViewPagerAdapter(this.supportFragmentManager,fragmentList,titleList)


        mainTabLayout?.setupWithViewPager(mainViewPager)


    }
}
