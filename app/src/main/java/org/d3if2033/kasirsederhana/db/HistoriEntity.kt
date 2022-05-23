package org.d3if2033.kasirsederhana.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "histori")
data class HistoriEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggalPembelian: Long = System.currentTimeMillis(),
    var total: String
)
