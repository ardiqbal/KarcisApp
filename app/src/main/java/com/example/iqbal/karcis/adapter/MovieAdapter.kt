package com.example.iqbal.karcis.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iqbal.karcis.R
import com.example.iqbal.karcis.activity.MovieDetailActivity
import com.example.iqbal.karcis.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val item : List<Movie>, val context : Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])

        holder.itemView.setOnClickListener {
            Log.i("Coba", "" + position)

            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("item_name", item[position].name)
            intent.putExtra("item_image", item[position].image)
            intent.putExtra("item_overview", item[position].overview)
            context.startActivity(intent)
        }
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(data : Movie){
            itemView.img_item.setImageResource(data.image)
            itemView.text_item.text = data.name
        }
    }
}