package com.bootcamp.magic.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Card(
    val multiverseid:Int,
    val name:String,
    val imageUrl:String?,
    val set:String,
    var favorite:Boolean = false,
    val types:ArrayList<String>) :
    Parcelable
//NEED -> NAME:STRING,ID:INT,URLIMAGE:STRING,TYPE:ARRAY<STRING>,FAVORITE,SET