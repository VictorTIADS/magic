package com.bootcamp.magic.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bootcamp.magic.Database.converter.ArrayListConverter

open class CardView
class Header(val setName: String) : CardView()

@Entity
class Item(
    @PrimaryKey(autoGenerate = true)
    val multiverseid:Int,
    val name:String,
    val imageUrl:String?,
    val set:String,
    val favorite:Boolean = false,
    @TypeConverters(ArrayListConverter::class)
    val types:ArrayList<String>) : CardView()

class Category(
    val title: String,
    val itens: ArrayList<Item>): CardView()

