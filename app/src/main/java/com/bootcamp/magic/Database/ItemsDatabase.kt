package com.bootcamp.magic.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bootcamp.magic.Database.converter.ArrayListConverter
import com.bootcamp.magic.Database.dao.ItemsDAO
import com.bootcamp.magic.Models.Item

@Database(entities = [Item::class], version = 1)
@TypeConverters(ArrayListConverter::class)
abstract class  ItemsDatabase : RoomDatabase()  {

    abstract fun itemsDAO(): ItemsDAO

    companion object {
        var INSTANCE: ItemsDatabase? = null

        fun getAppDataBase(context: Context): ItemsDatabase? {
            if (INSTANCE == null){
                synchronized(ItemsDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, ItemsDatabase::class.java, "CardsFavorites4.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}