package com.bootcamp.magic.repository

import android.util.Log
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Sets
import com.bootcamp.magic.network.RetrofitConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceRequestRepository() {

    var total_count_Sets:Int? = null



    fun getSetsFromApi(Success: (data: Sets) -> Unit,error: (message: String) -> Unit) {
        Log.i("aspk", "REPOSITORY SET CHAMADO")
        val request = RetrofitConfig().interfaceData()
        request.getSets().enqueue(object : Callback<Sets>{
            override fun onFailure(call: Call<Sets>, t: Throwable) {
                Log.i("aspk", "ERROR FROM GET SET: ${t.message}")
                error(t.message.toString())
            }

            override fun onResponse(call: Call<Sets>, response: Response<Sets>) {
                if (response.isSuccessful) {
                    if (response.body()!=null){
                        val body = response.body()
                        if (body != null) {
                            Log.i("aspk", "SUCCESS FROM GET SET: ${response.raw().request()}")
                            Success(body)
                        }
                    }
                }
            }
        })
    }

     fun getCardsFromApi(set:String?, page:Int, Succes: (data: Cards, total_count:Int?) -> Unit, error: (message: String) -> Unit) {
        Log.i("aspk", "REPOSITORY CARDS CHAMADO")
        val request = RetrofitConfig().interfaceData()
            request.getCards(set = set, page = page).enqueue(object : Callback<Cards>{
                override fun onFailure(call: Call<Cards>, t: Throwable) {
                    Log.i("aspk", "ERROR FROM GET CADS: ${t.message}")
                    error(t.message.toString())
                }

                override fun onResponse(call: Call<Cards>, response: Response<Cards>) {
                    if (response.isSuccessful) {
                        if (response.body()!=null){
                            val body = response.body()
                            Succes(body!!,response.headers().get("total-count")?.toInt())
                            Log.i("aspk", "SUCCESS FROM GET CADS: ${response.raw().request()}")
                        }
                    }
                }
            })

    }
}