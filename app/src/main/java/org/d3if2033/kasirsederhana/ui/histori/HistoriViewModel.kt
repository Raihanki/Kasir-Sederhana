package org.d3if2033.kasirsederhana.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2033.kasirsederhana.db.HistoriDao

class HistoriViewModel(private val db: HistoriDao): ViewModel() {

    val data = db.getAllHistori();

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}