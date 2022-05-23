package org.d3if2033.kasirsederhana.ui.kasir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2033.kasirsederhana.db.HistoriDao
import org.d3if2033.kasirsederhana.db.HistoriEntity

class KasirViewModel(private val db: HistoriDao): ViewModel() {

    val data = db.getAllHistori();

    fun saveHistori(total: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataHistori = HistoriEntity(
                    total = total
                )
                db.insert(dataHistori)
            }
        }
    }
}