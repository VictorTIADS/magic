package com.bootcamp.magic.Models

data class Card(val multiverseid:Int,val name:String,val imageUrl:String,val set:String,val types:ArrayList<String>)
//NEED -> NAME:STRING,ID:INT,URLIMAGE:STRING,TYPE:ARRAY<STRING>,FAVORITE,SET