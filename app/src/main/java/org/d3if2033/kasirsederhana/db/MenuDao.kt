package org.d3if2033.kasirsederhana.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MenuDao {
    @Insert
    fun insert(menu: MenuEntity)

    @Query("SELECT * FROM menus ORDER BY nama")
    fun getALlMenu(): LiveData<List<MenuEntity>>
}