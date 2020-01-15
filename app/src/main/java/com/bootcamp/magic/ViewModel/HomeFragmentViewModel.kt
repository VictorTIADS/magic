package com.bootcamp.magic.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.magic.Models.*
import com.bootcamp.magic.Models.Constants.Companion.Artifact
import com.bootcamp.magic.Models.Constants.Companion.Conspiracy
import com.bootcamp.magic.Models.Constants.Companion.Creature
import com.bootcamp.magic.Models.Constants.Companion.Enchantment
import com.bootcamp.magic.Models.Constants.Companion.Instant
import com.bootcamp.magic.Models.Constants.Companion.Land
import com.bootcamp.magic.Models.Constants.Companion.Phenomenon
import com.bootcamp.magic.Models.Constants.Companion.Plane
import com.bootcamp.magic.Models.Constants.Companion.Planeswalker
import com.bootcamp.magic.Models.Constants.Companion.Scheme
import com.bootcamp.magic.Models.Constants.Companion.Sorcery
import com.bootcamp.magic.Models.Constants.Companion.Tribal
import com.bootcamp.magic.Models.Constants.Companion.Vanguard
import com.bootcamp.magic.repository.ServiceRequestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList

class HomeFragmentViewModel : ViewModel() {

    private var page: Int = 1
    var total_count = 0
    var count = 0
    val sep : MutableMap<String, ArrayList<Item>> = mutableMapOf()
    val objectList = MutableLiveData<BaseModel<ArrayList<CardView>>>()
    val setName = MutableLiveData<String>()
    var list = Cards(arrayListOf())
    val dataCard = MutableLiveData<BaseModel<Cards>>()
    val dataSet = MutableLiveData<BaseModel<Sets>>()
    var service = ServiceRequestRepository()


    companion object {
        private val instancia: HomeFragmentViewModel = HomeFragmentViewModel()
        fun getInstance(): HomeFragmentViewModel = instancia
    }

    init {
        Log.i("aspk", "INIT VIEWMODEL")
        getSets()
    }

    fun getCardsList(): Items {
        val items = Items(arrayListOf())
        dataCard.value?.data?.cards?.map {
            items.items.add(Item(it.multiverseid, it.name, it.imageUrl, it.set, it.types))
        }
        return items
    }

    fun getSetList() = dataSet.value?.data
    fun getPage() = page
    fun getSetName() = setName.value
    fun getObjectList() = objectList.value?.data

    fun getSets() {
        dataSet.value = BaseModel(null, BaseModel.Companion.STATUS.LOADING, null)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                service.getSetsFromApi({ setsFromrepository ->

                    val listCorrect =
                        setsFromrepository.sets.sortedWith(compareByDescending { it.releaseDate })
                    val setsCorrect = Sets(listCorrect)
                    dataSet.value = BaseModel(setsCorrect, BaseModel.Companion.STATUS.SUCCESS, null)
                    setName.value = setsCorrect.sets[27].name

                }, {
                    dataSet.value = BaseModel(null, BaseModel.Companion.STATUS.ERROR, it)
                })
            }
        }

    }


    fun getSetCodeAtPosition(position: Int): String? = when (dataSet.value?.status) {
        BaseModel.Companion.STATUS.SUCCESS -> {
            dataSet.value!!.data?.sets?.get(position)?.code
        }
        else -> {
            null
        }
    }

    fun getSetNameAtPosition(position: Int): String? = when (dataSet.value?.status) {
        BaseModel.Companion.STATUS.SUCCESS -> {
            dataSet.value!!.data?.sets?.get(position)?.name
        }
        else -> {
            null
        }
    }


    fun getCards(set: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.i("aspk", "PAGE: $page")
                service.getCardsFromApi(set, page, { cards, _ ->
                    Log.i("aspk", "LIST SIZE: ${cards.cards.size}")
                    if (cards.cards.size < 100) {
                        list.cards.addAll(cards.cards)
                        val objects = arrayListOf<CardView>()
                        objects.add(Header(getSetNameAtPosition(27) ?: ""))
                        list.cards.forEach { card ->
                            card.types.forEach { type ->
                                if (!sep.containsKey(type)) sep[type] = ArrayList()
                                sep[type]?.add(
                                    Item(
                                        card.multiverseid,
                                        card.name,
                                        card.imageUrl,
                                        card.set,
                                        card.types
                                    )
                                )
                            }
                        }

                        sep.forEach {
                            objects.add( Category( it.key, it.value))
                        }

                        objectList.value =
                            BaseModel(objects, BaseModel.Companion.STATUS.SUCCESS, null)
                        dataCard.value = BaseModel(list, BaseModel.Companion.STATUS.SUCCESS, null)
                    } else {
                        page++
                        list.cards.addAll(cards.cards)
                        dataCard.value = BaseModel(
                            list,
                            BaseModel.Companion.STATUS.SUCCESS_STILL_HAVE_MORE,
                            null
                        )
                        Log.i(
                            "aspk",
                            "COMPARATION: list : ${list.cards.size} dataCard: ${dataCard.value?.data?.cards?.size}"
                        )
                        getCards(getSetCodeAtPosition(20))
                    }
                }, {
                    dataCard.value = BaseModel(null, BaseModel.Companion.STATUS.ERROR, it)
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