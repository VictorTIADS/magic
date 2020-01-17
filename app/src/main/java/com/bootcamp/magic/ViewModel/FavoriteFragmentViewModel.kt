package com.bootcamp.magic.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.magic.Database.dao.ItemsDAO
import com.bootcamp.magic.Models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList

class FavoriteFragmentViewModel(val itemsDAO: ItemsDAO):ViewModel() {

    val objectList = MutableLiveData<BaseModel<ArrayList<CardView>>>()

    fun getFavorities(){
        val lasValue = objectList.value?.data
        val sep : MutableMap<String, ArrayList<Item>> = mutableMapOf()
        viewModelScope.launch {
            val objects = arrayListOf<CardView>()
            withContext(Dispatchers.IO){
                try {
                    val itens = itemsDAO.getItemRoom()
                    if (itens!=lasValue){
                        objects.add(Header(itens[0].set)) // TODO PEGAR NOME DO SET...
                        itens.forEach { item ->
                            item.types.forEach { type ->
                                if (!sep.containsKey(type)) sep[type] = ArrayList()
                                sep[type]?.add( item )
                            }
                        }

                        sep.forEach {
                            objects.add( Category( it.key, it.value))
                        }

                    }else{
                        isSuccess(lasValue)
                    }

                    isSuccess(objects)
                }catch (e:Exception){
                    isFail()
                }
            }

        }
    }
    fun isSuccess(objects: ArrayList<CardView>){
        objectList.postValue(BaseModel(objects, BaseModel.Companion.STATUS.SUCCESS, null))
    }
    fun isFail(){
        objectList.postValue(BaseModel(null, BaseModel.Companion.STATUS.ERROR, null))
    }
}