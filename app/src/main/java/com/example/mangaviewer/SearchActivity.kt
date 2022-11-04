package com.example.mangaviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mangaviewer.fragment.SearchFragment

class SearchActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_container, SearchFragment(),"searchFragment")
        transaction.commit()
    }
}
