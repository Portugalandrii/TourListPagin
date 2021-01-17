package com.portugal1576.tourlistpagin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portugal1576.tourlistpagin.R
import com.portugal1576.tourlistpagin.data.Entity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: Entity?) {
        if (news != null) {
            itemView.txt_news_name.text = news.title
            Picasso.get().load("https://tourstart.org/"+news.image).into(itemView.img_news_banner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}