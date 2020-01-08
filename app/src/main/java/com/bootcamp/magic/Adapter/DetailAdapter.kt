package com.bootcamp.magic.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_item_list.view.*

class DetailAdapter(val context: Context, val cardsList: ArrayList<Card>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = cardsList[0]
        val image = holder.itemView.image_item
        Picasso.get().load(card.imageUrl).resize(223, 310).centerCrop()
            .placeholder(R.drawable.magic_place_holder).into(image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_item_list, parent, false)
        return ViewlHolder(view)
    }

    override fun getItemCount() = cardsList.size
}

class ViewlHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)