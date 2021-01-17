package com.portugal1576.tourlistpagin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portugal1576.tourlistpagin.R
import com.portugal1576.tourlistpagin.data.State
import com.portugal1576.tourlistpagin.data.State.ERROR
import com.portugal1576.tourlistpagin.data.State.LOADING

import kotlinx.android.synthetic.main.item_list_footer.view.*


class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: State?) {

        itemView.progress_bar.visibility = if (status == State.LOADING) VISIBLE else View.INVISIBLE
        itemView.txt_error.visibility = if (status == State.ERROR) VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_footer, parent, false)
            view.txt_error.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }
}