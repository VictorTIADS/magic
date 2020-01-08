package com.bootcamp.magic.Interface

import com.bootcamp.magic.Models.Sets
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestInterface {
    @GET("{endpoint}")
    fun getSets(
        @Path("endpoint")endPoint:String = "sets",
        @Query("name")name:String = "",
        @Query("block")block:String = ""
    ): Call<Sets>

    @GET("{endpoint}")
    fun getCards(
        @Path("endpoint")endPoint:String = "cards",
        @Query("set")set:String,
        @Query("multiverseid")multiverseid:String
    )
}