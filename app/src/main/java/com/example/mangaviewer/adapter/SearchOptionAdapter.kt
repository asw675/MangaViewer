package com.example.mangaviewer.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangaviewer.R
import com.example.mangaviewer.model.AnimeItem

class SearchOptionAdapter(val mContext: Context?, var optionMap:HashMap<String,String>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    var optionList = ArrayList<String>()
    var mOnItemClickListener:OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    init {
        for((key,value) in optionMap!!){
            optionList.add(key)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater
            .from(mContext)
            .inflate(R.layout.item_option, parent, false)
        return SearchOptionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            if(mOnItemClickListener!=null)
                mOnItemClickListener?.onItemClick(position)
        }

        holder.itemView.findViewById<TextView>(R.id.tv_option).text = optionList[position]
    }

    override fun getItemCount(): Int = optionList.size

    public fun setItem(optionMap:HashMap<String,String>?){
        optionList.clear()
        for((key,value) in optionMap!!){
            optionList.add(key)
        }
        notifyDataSetChanged()
    }

    inner class SearchOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

