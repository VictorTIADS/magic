package com.bootcamp.magic.Models.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.magic.Interface.RecycleViewInterface
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card.view.*

class CardsAdapter(var cardList: Cards,val interfaceClick:RecycleViewInterface) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = cardList.cards[position]
        val imageView_Card = holder.itemView.item_card_home
        holder.itemView.setOnClickListener {
            interfaceClick.GoToDetails(cardList,position)
        }
        Picasso.get().load(card.imageUrl.convertToHttps())
            .placeholder(R.drawable.card_placeholder)
            .error(R.drawable.card_placeholder)
            .into(imageView_Card)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        return ViewlHolder(view)
    }

    override fun getItemCount() = cardList.cards.size

    fun addItems(newCardList: Cards){
        cardList.cards.clear()
        cardList = newCardList
        notifyDataSetChanged()
    }

    private fun String?.convertToHttps() = this?.replace("http://", "https://")

}
class ViewlHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)
