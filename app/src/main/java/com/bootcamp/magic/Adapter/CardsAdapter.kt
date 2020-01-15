package com.bootcamp.magic.Models.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.magic.Interface.RecycleViewInterface
import com.bootcamp.magic.Models.*
import com.bootcamp.magic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header_set.view.*
import kotlinx.android.synthetic.main.header_type.view.*
import kotlinx.android.synthetic.main.item_card.view.*

class CardsAdapter(var items: List<CardView>, val interfaceClick: RecycleViewInterface) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var cards = Cards(arrayListOf())


    companion object {
        val HEADER_SET = 0
        val HEADER_TYPE = 1
        val ITEM = 2
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] is Header -> {
                HEADER_SET
            }
            items[position] is Type -> {
                HEADER_TYPE
            }
            items[position] is Item -> {
                ITEM
            }
            else -> {
                -1
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            HEADER_SET -> {
                val viewHolderSet = holder as ViewHolderSet
                configureViewHolderSet(viewHolderSet, position)
            }
            HEADER_TYPE -> {
                val viewHolderType = holder as ViewHolderType
                configureViewHolderType(viewHolderType, position)
            }
            ITEM -> {
                val item = items[position] as Item
                val sap = Card(item.multiverseid,item.name,item.imageUrl,item.set,item.types)
                cards.cards.add(sap)

                val viewHolderItem = holder as ViewHolderItem
                configureViewHolderItem(viewHolderItem, position)

                viewHolderItem.itemView.setOnClickListener {
                    interfaceClick.GoToDetails(cards,position)

                }
            }
            else -> {
                error("Could Not Bind")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            HEADER_SET -> {
                val viewSet = inflater.inflate(R.layout.header_set, parent, false)
                viewHolder = ViewHolderSet(viewSet)
            }
            HEADER_TYPE -> {
                val viewType = inflater.inflate(R.layout.header_type, parent, false)
                viewHolder = ViewHolderType(viewType)
            }
            ITEM -> {
                val viewItem = inflater.inflate(R.layout.item_card, parent, false)
                viewHolder = ViewHolderItem(viewItem)
            }
            else -> {
                error("Could not inflate de view at onCreate")
            }
        }
        return viewHolder
    }


    fun configureViewHolderSet(viewHolderSet: ViewHolderSet, position: Int) {
        val headerSet = items[position] as Header
        if (headerSet != null) {
            viewHolderSet.title.text = headerSet.setName
        }

    }

    fun configureViewHolderType(viewHolderType: ViewHolderType, position: Int) {
        val headerType = items[position] as Type
        if (headerType != null) {
            viewHolderType.type.text = headerType.type
        }
    }

    fun configureViewHolderItem(viewHolderItem: ViewHolderItem, position: Int) {
        val item = items[position] as Item

        if (item != null) {
            Picasso.get().load(item.imageUrl.convertToHttps())
                .placeholder(R.drawable.card_placeholder)
                .error(R.drawable.card_placeholder)
                .into(viewHolderItem.imageView)
        }


    }

    private fun String?.convertToHttps() = this?.replace("http://", "https://")

}

class ViewHolderSet(view: View) : RecyclerView.ViewHolder(view) {
    var title: TextView = view.header_set
}

class ViewHolderType(view: View) : RecyclerView.ViewHolder(view) {
    var type: TextView = view.header_type
}

class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
    var imageView: ImageView = view.item_card_home
}
