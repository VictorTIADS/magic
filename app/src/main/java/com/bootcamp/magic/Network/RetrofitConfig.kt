package com.bootcamp.magic.network

import com.bootcamp.magic.Interface.RequestInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitConfig(){

    companion object{
        var BASE_URL = "https://api.magicthegathering.io/v1/"
    }



    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(40, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun interfaceData() = retrofit.create(RequestInterface::class.java)
}