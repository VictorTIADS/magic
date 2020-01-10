package com.bootcamp.magic.repository

import android.util.Log
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Sets
import com.bootcamp.magic.network.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceRequestRepository() {

    fun getSetsFromApi(Succes: (data: Sets) -> Unit,error: (message: String) -> Unit) {
        val request = RetrofitConfig().interfaceData()
        request.getSets().enqueue(object : Callback<Sets>{
            override fun onFailure(call: Call<Sets>, t: Throwable) {
                Log.d("resultado", t.message.toString())
                error(t.message.toString())
            }

            override fun onResponse(call: Call<Sets>, response: Response<Sets>) {
                Log.d("resultado", response.toString())
                if (response.isSuccessful) {

                    if (response.body()!=null){
                        Succes(response.body()!!)
                    }
                }
            }
        })
    }

    fun getCardsFromApi(set:String?,page:Int,Succes: (data: Cards) -> Unit,Error: (message: String) -> Unit) {
        val request = RetrofitConfig().interfaceData()
        request.getCards(set = set,page = page).enqueue(object : Callback<Cards>{
            override fun onFailure(call: Call<Cards>, t: Throwable) {
                Log.d("resultado", t.message.toString())
                Error(t.message)
            }

            override fun onResponse(call: Call<Cards>, response: Response<Cards>) {
                Log.d("resultado", response.toString())
                if (response.isSuccessful) {

                    if (response.body()!=null){
                        Succes(response.body()!!)
                    }
                }
            }
        })
    }




}