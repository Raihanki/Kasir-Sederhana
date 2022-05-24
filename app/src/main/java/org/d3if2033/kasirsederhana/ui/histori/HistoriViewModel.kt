package org.d3if2033.kasirsederhana.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if2033.kasirsederhana.db.HistoriDao

class HistoriViewModel(db: HistoriDao): ViewModel() {

    val data = db.getAllHistori();
}