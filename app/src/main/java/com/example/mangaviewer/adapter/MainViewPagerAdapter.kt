package com.example.mangaviewer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainViewPagerAdapter
    (fm: FragmentManager,
     private val mFragments: List<Fragment>,
     private val mTitle: List<String>): FragmentPagerAdapter(fm, BEHAVIOR_SET_USER_VISIBLE_HINT) {
    //返回指定fragment
    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    //返回fragment数量
    override fun getCount(): Int {
        return mFragments.size
    }

    //获取分页标题
    override fun getPageTitle(position: Int): CharSequence? {
        return mTitle[position]
    }
}