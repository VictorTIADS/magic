package com.bootcamp.magic.Models

sealed class CardView()

class Header(val setName: String) : CardView()
class Type(val type: String) : CardView()
class Item(val multiverseid:Int,val name:String,val imageUrl:String?,val set:String,val types:ArrayList<String>) : CardView()


