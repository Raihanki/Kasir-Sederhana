package org.d3if2033.kasirsederhana.ui.kasir

import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.d3if2033.kasirsederhana.db.MenuDao
import org.d3if2033.kasirsederhana.db.MenuDb

class KasirViewModel(db: MenuDao) : ViewModel() {

    val data = db.getALlMenu();

}