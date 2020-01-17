package com.bootcamp.magic.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.magic.Database.dao.ItemsDAO
import com.bootcamp.magic.Models.BaseModel
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragmentViewModel(val itemsDAO: ItemsDAO) : ViewModel() {

    var dataCard = MutableLiveData <BaseModel<Cards>>()
    var list = Cards(arrayListOf())

    fun converterCardOFItem(card: Card): Item{
        return Item(
            card.multiverseid,
            card.name,
            card.imageUrl,
            card.set,
            card.favorite,
            card.types
        )
    }

    fun insertCard(item: Item){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                itemsDAO.insertItem(item)
            }
        }
    }

    fun deleteCard(item: Item){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                itemsDAO.deleteItem(item)
            }
        }
    }

    fun getCardRoom(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val itens = itemsDAO.getItemRoom()
                itens.forEach{
                    val sap = Card(it.multiverseid, it.name, it.imageUrl, it.set,it.favorite, it.types)
                    list.cards.add(sap)
                }
            }
            dataCard.value = BaseModel(list, BaseModel.Companion.STATUS.SUCCESS,null)
            dataCard.postValue(dataCard.value)
        }
    }

    fun checkFavoritesCards(cardsList: ArrayList<Card>):ArrayList<Card>{
        dataCard.value?.data?.cards?.forEach {cardRoom ->
            cardsList.map {card ->
                if (card.multiverseid == cardRoom.multiverseid){
                    card.favorite = true
                }
            }
        }
        return cardsList
    }
}
