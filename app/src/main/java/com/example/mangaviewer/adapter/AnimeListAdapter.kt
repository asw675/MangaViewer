package com.example.mangaviewer.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaviewer.model.AnimeItem
import android.widget.Toast

import android.view.View

import android.view.LayoutInflater
import android.widget.ImageView

import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mangaviewer.R

class AnimeListAdapter(val mContext: Context?, var aniList:ArrayList<AnimeItem>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater
            .from(mContext)
            .inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_anime_title).text = aniList?.get(position)?.title
        holder.itemView.findViewById<TextView>(R.id.tv_anime_desc).text = "Score: " + aniList?.get(position)?.score
        val cover:ImageView = holder.itemView.findViewById<ImageView>(R.id.img_anime_cover)
        Glide.with(mContext!!).load(aniList?.get(position)?.cover).into(cover)
    }

    override fun getItemCount(): Int = aniList!!.size

    public fun setItem(aniList:ArrayList<AnimeItem>?){
        this.aniList = aniList
        notifyDataSetChanged()
    }

    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

