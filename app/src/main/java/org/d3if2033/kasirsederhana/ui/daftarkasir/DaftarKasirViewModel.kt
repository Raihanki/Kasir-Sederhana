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
        retriveData()
    }

    private fun retriveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(KasirApi.service.getKasir());
            } catch (e: Exception) {
                Log.d("DaftarKasirViewModel", "Failure: ${e.message}");
            }
        }
    }

    fun getData() : LiveData<List<Kasir>> = data;
}