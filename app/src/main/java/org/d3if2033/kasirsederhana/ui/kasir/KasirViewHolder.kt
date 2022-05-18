package org.d3if2033.kasirsederhana.ui.kasir

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.d3if2033.kasirsederhana.databinding.ListMenuBinding
import org.d3if2033.kasirsederhana.ui.kasir.model.Menu

class KasirViewHolder(private val binding: ListMenuBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(menu: Menu) = with(binding) {
        chkMenu.text = menu.nama
        txtHarga.text = menu.harga
    }
}