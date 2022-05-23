package org.d3if2033.kasirsederhana.ui.kasir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2033.kasirsederhana.db.HistoriDao

class KasirViewModelFactory(
    private val db: HistoriDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KasirViewModel::class.java)) {
            return KasirViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}