package com.example.iqbal.karcis.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iqbal.karcis.R
import com.example.iqbal.karcis.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val item : List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(data : Movie){
            itemView.img_item.setImageResource(data.image)
            itemView.text_item.text = data.name
        }
    }
}