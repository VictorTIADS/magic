package com.bootcamp.magic.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Item
import com.bootcamp.magic.Models.Items
import com.bootcamp.magic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_item_list.view.*

class DetailAdapter(var context: Context, var cardsList: Cards) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = cardsList.cards[position]
        val image = holder.itemView.image_item
        image.setImageResource(R.drawable.magic_place_holder)
        Picasso.get().load(card.imageUrl.convertToHttps())
            .placeholder(R.drawable.magic_place_holder)
            .error(R.drawable.magic_place_holder)
            .into(image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_item_list, parent, false)
        return ViewlHolder(view)
    }

    private fun String?.convertToHttps() = this?.replace("http://", "https://")

    override fun getItemCount() = cardsList.cards.size
}

class ViewlHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)