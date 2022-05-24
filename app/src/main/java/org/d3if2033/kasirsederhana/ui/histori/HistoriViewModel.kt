package org.d3if2033.kasirsederhana.ui.histori

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2033.kasirsederhana.db.HistoriDao
import org.d3if2033.kasirsederhana.db.HistoriEntity

class HistoriViewModel(private val db: HistoriDao): ViewModel() {
    private var isDeleted = MutableLiveData<Boolean>()
    val getIsDeleted: LiveData<Boolean> get() = (isDeleted)

    val data = db.getAllHistori();

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

    fun deleteData(histori: HistoriEntity) {
        isDeleted.postValue(false)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.deleteData(histori)
                isDeleted.postValue(true)
            }
        }
    }
}