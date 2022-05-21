package org.d3if2033.kasirsederhana.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menus")
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var nama: String,
    var deskripsi: String,
    var harga: String
)