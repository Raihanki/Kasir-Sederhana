package org.d3if2033.kasirsederhana.ui.tambahmenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2033.kasirsederhana.db.MenuDao

class TambahViewModelFactory(private val db: MenuDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TambahMenuViewModel::class.java)) {
            return TambahMenuViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}