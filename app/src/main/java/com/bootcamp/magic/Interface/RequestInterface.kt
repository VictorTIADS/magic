package com.bootcamp.magic.Interface

import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Sets
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RequestInterface {
    @GET("{endpoint}")
    fun getSets(
        @Path("endpoint")endPoint:String = "sets",
        @Query("name")name:String = ""
    ): Call<Sets>

    @GET("{endpoint}")
    fun getCards(
        @Path("endpoint")endPoint:String = "cards",
        @Query("set")set:String?,
        @Query("orderBy")orderBy:String = "types",
        @Query("multiverseid")multiverseid:String = "",
        @Query("page")page:Int? = 1
    ):Call<Cards>

}