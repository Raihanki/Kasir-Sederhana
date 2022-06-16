package org.d3if2033.kasirsederhana.ui.daftarkasir

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2033.kasirsederhana.model.Kasir
import org.d3if2033.kasirsederhana.network.KasirApi

class DaftarKasirViewModel : ViewModel() {

    private val data = MutableLiveData<List<Kasir>>()
    init {
        data.value
        retriveData()
    }

    private fun retriveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val result = KasirApi.service.getKasir();
                Log.d("DaftarKasirViewModel", "Success: $result");
            } catch (e: Exception) {
                Log.d("DaftarKasirViewModel", "Failure: ${e.message}");
            }
        }
    }

    fun getData() : LiveData<List<Kasir>> = data;
}