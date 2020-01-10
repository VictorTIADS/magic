package com.bootcamp.magic.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.magic.Models.BaseModel
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Sets
import com.bootcamp.magic.repository.ServiceRequestRepository

class HomeFragmentViewModel(): ViewModel() {

    var page : Int = 1
    val dataCard = MutableLiveData <BaseModel<Cards>>()
    val dataSet = MutableLiveData <BaseModel<Sets>>()


    var  service = ServiceRequestRepository()

    fun getCardsList() = dataCard.value?.data

    fun getSets(){
        dataSet.value = BaseModel(null, BaseModel.Companion.STATUS.LOADING,null)
        service.getSetsFromApi({
            dataSet.value = BaseModel(it, BaseModel.Companion.STATUS.SUCCESS,null)
            orderSets()

        },{
            dataSet.value = BaseModel(null, BaseModel.Companion.STATUS.ERROR,it)
        })
    }
    


    fun getSetCodeAtPosition(position:Int): String? = when (dataSet.value?.status) {
       BaseModel.Companion.STATUS.SUCCESS -> {
           dataSet.value!!.data?.sets?.get(position)?.code
       }
       else -> {
           null
       }
   }

    fun orderSets(){
        val sortedList = dataSet.value?.data?.sets?.sortedWith( compareByDescending {it.releaseDate})
        if (sortedList != null) {
            dataSet.value?.data?.sets = sortedList
        }

    }

    fun getAllCards(){

    }

    fun getCards(set:String?){

        dataCard.value = BaseModel(null, BaseModel.Companion.STATUS.LOADING,null)
        service.getCardsFromApi(set,page,{ cards, list_count ->

            dataCard.value = BaseModel(cards, BaseModel.Companion.STATUS.SUCCESS,null)

        },{
            dataCard.value = BaseModel(null, BaseModel.Companion.STATUS.ERROR,it)
        })
        getAllCards()
    }

    /*fun orderCards(){
        val sortedList = dataSet.value?.data?.sets?.sortedWith( compareByDescending {it.releaseDate})
        if (sortedList != null) {
            dataSet.value?.data?.sets = sortedList
        }


    }*/


}