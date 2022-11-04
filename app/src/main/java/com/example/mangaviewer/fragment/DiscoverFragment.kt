package com.example.mangaviewer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaviewer.R
import com.example.mangaviewer.adapter.DiscoverAdapter
import com.example.mangaviewer.databinding.FragmentDiscoverBinding
import com.example.mangaviewer.model.DiscoverList
import com.example.mangaviewer.viewmodel.DiscoverViewModel

class DiscoverFragment : Fragment() {

    var discoverViewModel: DiscoverViewModel? = null
    var binding: FragmentDiscoverBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        discoverViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(DiscoverViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_discover,container,false)

        var discoverList = ArrayList<DiscoverList>()
        discoverList.add(DiscoverList())
        discoverList.add(DiscoverList())
        discoverList.add(DiscoverList())
        discoverList.add(DiscoverList())
        binding!!.discoverRecy.adapter = DiscoverAdapter(context,discoverList)
        (binding!!.discoverRecy.layoutManager as LinearLayoutManager).orientation = RecyclerView.VERTICAL
        return binding?.root
    }

}