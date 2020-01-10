package com.bootcamp.magic.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.magic.Models.BaseModel
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Sets
import com.bootcamp.magic.repository.ServiceRequestRepository

class HomeFragmentViewModel(): ViewModel() {

    var page = 1
    val dataCard = MutableLiveData <BaseModel<Cards>>()
    val dataSet = MutableLiveData <BaseModel<Sets>>()


    var  service = ServiceRequestRepository()

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

    fun getCards(set:String?){

        dataCard.value = BaseModel(null, BaseModel.Companion.STATUS.LOADING,null)
        service.getCardsFromApi(set,page,{
            dataCard.value = BaseModel(it, BaseModel.Companion.STATUS.SUCCESS,null)
        },{
            dataCard.value = BaseModel(null, BaseModel.Companion.STATUS.ERROR,it)
        })
    }


}