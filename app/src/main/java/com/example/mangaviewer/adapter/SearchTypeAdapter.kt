package com.example.mangaviewer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaviewer.R

class SearchTypeAdapter (val mContext: Context?,  var optionMap:HashMap<String,HashMap<String, String>>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    var optionTitleList = ArrayList<String>()

    init {
        for((key,value) in optionMap!!){
            optionTitleList.add(key)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater
            .from(mContext)
            .inflate(R.layout.item_advanced_search, parent, false)
        return SearchTypeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val recyOption = holder.itemView.findViewById<RecyclerView>(R.id.recy_option)
        holder.itemView.findViewById<TextView>(R.id.tv_option_type)?.text = optionTitleList[position]
        recyOption?.adapter = SearchOptionAdapter(mContext,
            optionMap?.get(optionTitleList[position])
        )
        (recyOption?.adapter as SearchOptionAdapter)
            .setOnItemClickListener(object:SearchOptionAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

            }
        })
        val layoutManager = GridLayoutManager(mContext,2)
        recyOption?.layoutManager = layoutManager
    }

    override fun getItemCount(): Int = optionTitleList.size


    public fun setItem(optionMap:HashMap<String,HashMap<String, String>>?){
        this.optionMap = optionMap
        optionTitleList.clear()
        for((key,value) in optionMap!!){
            optionTitleList.add(key)
        }
        notifyDataSetChanged()
    }

    inner class SearchTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

