package com.example.mangaviewer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangaviewer.model.DiscoverList

class DiscoverViewModel: ViewModel() {
    var discoverList: MutableLiveData<ArrayList<DiscoverList>> = MutableLiveData()
}