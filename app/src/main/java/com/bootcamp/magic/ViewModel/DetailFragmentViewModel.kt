package com.bootcamp.magic.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.Models.Cards

class DetailFragmentViewModel : ViewModel(){

    val cardsList = MutableLiveData<Cards>()
    val indexList = MutableLiveData<Int>()


    fun initCardList(cards: Cards){
        cardsList.value = cards
    }
    fun initCardListIndex(index:Int){
        indexList.value = index
    }

    fun getCards():Cards = cardsList.value!!
    fun getIndexList():Int = indexList.value!!
}
