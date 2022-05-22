package org.d3if2033.kasirsederhana.ui.tambahmenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2033.kasirsederhana.db.MenuDao
import org.d3if2033.kasirsederhana.db.MenuEntity

class TambahMenuViewModel(private val db: MenuDao) : ViewModel() {

    val data = db.getALlMenu();

    fun insertNewMenu(nama: String, deskripsi: String, harga: String) {
        viewModelScope.launch { 
            withContext(Dispatchers.IO) {
                val dataMenu = MenuEntity(
                    nama = nama,
                    deskripsi = deskripsi,
                    harga = harga
                )
                db.insert(dataMenu);
            }
        }
    }
}