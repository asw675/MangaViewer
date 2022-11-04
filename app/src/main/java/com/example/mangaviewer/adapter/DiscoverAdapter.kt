package com.example.mangaviewer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.databinding.library.baseAdapters.BR
import com.example.mangaviewer.R
import com.example.mangaviewer.databinding.ItemDiscoverListBinding
import com.example.mangaviewer.model.DiscoverList
import java.util.ArrayList

class DiscoverAdapter(val mContext: Context?, var discoverList:ArrayList<DiscoverList>?) : RecyclerView.Adapter<DiscoverAdapter.BindingHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = DataBindingUtil.inflate<ItemDiscoverListBinding>(
            LayoutInflater.from(mContext),
            R.layout.item_discover_list,
            parent,
            false
        )
        return  BindingHolder(binding)
    }

    override fun getItemCount(): Int = discoverList!!.size

    inner class BindingHolder(private var binding : ItemDiscoverListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindData(discover:DiscoverList){
            binding.setVariable(BR.discoverList, discover)
            binding.executePendingBindings()
        }

    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bindData(discoverList!![position])
    }
}
