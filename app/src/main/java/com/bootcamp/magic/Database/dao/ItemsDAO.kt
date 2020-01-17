package com.bootcamp.magic.Database.dao

import androidx.room.*
import com.bootcamp.magic.Models.Item

@Dao
interface ItemsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

    @Query("SELECT * FROM Item")
    fun getItemRoom(): List<Item>

}
