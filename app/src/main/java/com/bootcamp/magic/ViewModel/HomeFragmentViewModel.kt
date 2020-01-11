package com.bootcamp.magic.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.magic.Models.BaseModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Sets
import com.bootcamp.magic.repository.ServiceRequestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel: ViewModel() {

    private var page : Int = 1
    var total_count = 0
    var count = 0
    val list = Cards(arrayListOf())
    val dataCard = MutableLiveData <BaseModel<Cards>>()
    val dataSet = MutableLiveData <BaseModel<Sets>>()
    var  service = ServiceRequestRepository()

    companion object {
        private val instancia: HomeFragmentViewModel = HomeFragmentViewModel()
        fun getInstance(): HomeFragmentViewModel = instancia
    }

    init {
        Log.i("aspk","INIT VIEWMODEL")
        getSets()
    }

    fun getCardsList() = dataCard.value?.data
    fun getSetList() = dataSet.value?.data
    fun getPage() = page

    fun getSets(){
        dataSet.value = BaseModel(null,BaseModel.Companion.STATUS.LOADING,null)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                service.getSetsFromApi({ setsFromrepository ->
                    if (setsFromrepository!=null){
                        val listCorrect = setsFromrepository.sets.sortedWith( compareByDescending {it.releaseDate})
                        val setsCorrect = Sets(listCorrect)
                        dataSet.value = BaseModel(setsCorrect,BaseModel.Companion.STATUS.SUCCESS,null)
                    }
                },{
                    dataSet.value = BaseModel(null, BaseModel.Companion.STATUS.ERROR,it)
                })
            }
        }

    }
    


    fun getSetCodeAtPosition(position:Int): String? = when (dataSet.value?.status) {
       BaseModel.Companion.STATUS.SUCCESS -> {
           dataSet.value!!.data?.sets?.get(position)?.code
       }
       else -> {
           null
       }
   }



    fun getCards(set:String?){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                Log.i("aspk","PAGE: $page")
                service.getCardsFromApi(set,page,{ cards, list_count ->
                    Log.i("aspk","LIST SIZE: ${cards.cards.size}")
                    if(cards.cards.size == 0){
                        dataCard.value = BaseModel(list, BaseModel.Companion.STATUS.SUCCESS,null)
                    }else{
                        page++
                        list.cards.addAll(cards.cards)
                        dataCard.value = BaseModel(list, BaseModel.Companion.STATUS.SUCCESS_STILL_HAVE_MORE,null)
                        Log.i("aspk","COMPARATION: list : ${list.cards.size} dataCard: ${dataCard.value?.data?.cards?.size}")
                        getCards(getSetCodeAtPosition(38))
                    }
                },{
                    dataCard.value = BaseModel(null, BaseModel.Companion.STATUS.ERROR,it)
                })
            }
        }

        //getAllCards()
    }

    /*fun orderCards(){
        val sortedList = dataSet.value?.data?.sets?.sortedWith( compareByDescending {it.releaseDate})
        if (sortedList != null) {
            dataSet.value?.data?.sets = sortedList
        }


    }*/


}