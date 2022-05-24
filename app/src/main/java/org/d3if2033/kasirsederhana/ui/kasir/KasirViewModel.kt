package org.d3if2033.kasirsederhana.ui.kasir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2033.kasirsederhana.db.HistoriDao
import org.d3if2033.kasirsederhana.db.HistoriEntity

class KasirViewModel(private val db: HistoriDao): ViewModel() {
    private var quantity = MutableLiveData<Int>()
    val getQuantity: LiveData<Int> get() = (quantity)

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
    fun hitungTotal(hargaSatuan: Int ,qty: Int){
        var total = 0
        total += (hargaSatuan*qty)
        quantity.value = total
    }
}