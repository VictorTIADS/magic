package com.bootcamp.magic.Models.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.magic.Interface.RecycleViewInterface
import com.bootcamp.magic.Models.*
import com.bootcamp.magic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header_set.view.*
import kotlinx.android.synthetic.main.item_card.view.*
import kotlinx.android.synthetic.main.item_category.view.*

class CardsAdapter(var items: List<CardView>, val interfaceClick: RecycleViewInterface?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var cards = Cards(arrayListOf())


    companion object {
        val HEADER_SET = 0
        val ITEM = 2
        val CATEGORY_TYPE = 3
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] is Header -> {
                HEADER_SET
            }
            items[position] is Item -> {
                ITEM
            }
            items[position] is Category -> {
                CATEGORY_TYPE
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
            ITEM -> {
                val item = items[position] as Item
                val sap = Card(item.multiverseid, item.name, item.imageUrl, item.set,item.favorite, item.types)
                cards.cards.add(sap)
                val viewHolderItem = holder as ViewHolderItem
                configureViewHolderItem(viewHolderItem, position)
                viewHolderItem.itemView.setOnClickListener {
                    interfaceClick?.GoToDetails(cards, position)
                }
            }
            CATEGORY_TYPE -> {
                val viewHolderCategory = holder as ViewHolderCategory
                configureViewHolderCategory(viewHolderCategory, position)
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
            ITEM -> {
                val viewItem = inflater.inflate(R.layout.item_card, parent, false)
                viewHolder = ViewHolderItem(viewItem)
            }
            CATEGORY_TYPE -> {
                val viewCategory = inflater.inflate(R.layout.item_category, parent, false)
                viewHolder = ViewHolderCategory(viewCategory)
            }
            else -> {
                error("Could not inflate de view at onCreate")
            }
        }
        return viewHolder
    }

    fun updateAdapter(list: List<CardView>){
        items = list
        notifyDataSetChanged()
    }


    fun configureViewHolderSet(viewHolderSet: ViewHolderSet, position: Int) {
        val headerSet = items[position] as Header
        if (headerSet != null) {
            viewHolderSet.title.text = headerSet.setName
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

    fun configureViewHolderCategory(viewHolderCategory: ViewHolderCategory, position: Int) {
        val category = items[position] as Category
        if (category != null) {
            viewHolderCategory.categoryTitle.text = category.title
            viewHolderCategory.categoryList.adapter =
                CardsAdapter(category.itens, this.interfaceClick)
            viewHolderCategory.categoryList.layoutManager =
                GridLayoutManager(viewHolderCategory.itemView.context, 3)
        }
    }

    private fun String?.convertToHttps() = this?.replace("http://", "https://")

}

class ViewHolderSet(view: View) : RecyclerView.ViewHolder(view) {
    var title: TextView = view.header_set
}

class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
    var imageView: ImageView = view.item_card_home
}

class ViewHolderCategory(view: View) : RecyclerView.ViewHolder(view) {
    var categoryList: RecyclerView = view.categoryList
    var categoryTitle: TextView = view.categoryTitle
}
