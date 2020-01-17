package com.bootcamp.magic.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Item
import com.bootcamp.magic.Models.Items

class DetailFragmentViewModel : ViewModel() {

    private val cardsList = MutableLiveData<Cards>()
    private val indexList = MutableLiveData<Int>()


    fun initCardList(cards: Cards) {
        cardsList.value = cards
    }

    fun initCardListIndex(index: Int) {
        indexList.value = index
    }

    fun getCards(): Cards = cardsList.value!!
    fun getIndexList(): Int = indexList.value!!
}
