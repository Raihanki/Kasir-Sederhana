package org.d3if2033.kasirsederhana.ui.daftarkasir

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2033.kasirsederhana.MainActivity
import org.d3if2033.kasirsederhana.model.Kasir
import org.d3if2033.kasirsederhana.network.ApiStatus
import org.d3if2033.kasirsederhana.network.KasirApi
import org.d3if2033.kasirsederhana.network.UpdateWorker
import java.util.concurrent.TimeUnit

class DaftarKasirViewModel : ViewModel() {

    private val status = MutableLiveData<ApiStatus>()
    private val data = MutableLiveData<List<Kasir>>()
    init {
        retriveData()
    }

    private fun retriveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(KasirApi.service.getKasir());
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("DaftarKasirViewModel", "Failure: ${e.message}");
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData() : LiveData<List<Kasir>> = data;

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build();

        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}