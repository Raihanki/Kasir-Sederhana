package org.d3if2033.kasirsederhana.ui.kasir

import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2033.kasirsederhana.db.MenuDao
import org.d3if2033.kasirsederhana.db.MenuDb
import org.d3if2033.kasirsederhana.db.MenuEntity

class KasirViewModel(db: MenuDao) : ViewModel() {

    val data = db.getALlMenu();
    //mutableLiveData bukan final
    private var dataMenu = MutableLiveData<ArrayList<MenuEntity>>()
    val getDataMenu: LiveData<ArrayList<MenuEntity>> get() = (dataMenu)

    fun hitungTotal(dataMenu: MenuEntity, isChecked: Boolean, qty: String): String {
        var total = 0;
        if (isChecked) {
            total += (dataMenu.harga.toInt() * qty.toInt())
            this.dataMenu.value = arrayListOf(dataMenu)
        }
        return total.toString()
    }


}