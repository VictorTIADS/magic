package com.bootcamp.magic.Models

sealed class CardView()

class Header(val setName: String) : CardView()
class Item(val multiverseid:Int,val name:String,val imageUrl:String?,val set:String,val types:ArrayList<String>) : CardView()
class Category( val title: String, val itens: ArrayList<Item>): CardView()

