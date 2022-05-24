package org.d3if2033.kasirsederhana.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoriDao {
    @Insert
    fun insert(histori: HistoriEntity)

    @Query("SELECT * FROM histori")
    fun getAllHistori(): LiveData<List<HistoriEntity>>
}