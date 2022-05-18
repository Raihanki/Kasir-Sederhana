package org.d3if2033.kasirsederhana.ui.kasir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.ListMenuBinding
import org.d3if2033.kasirsederhana.ui.kasir.model.Menu

class KasirAdapter(private val menu : List<Menu>) : RecyclerView.Adapter<KasirAdapter.KasirViewHolder>() {

    inner class KasirViewHolder(val binding: ListMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KasirViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val binding = ListMenuBinding.inflate(inflater, parent, false);
        return KasirViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KasirViewHolder, position: Int) {
        val i = menu[position]
        holder.binding.chkMenu.text = i.nama;
        holder.binding.txtHarga.text = i.harga;
    }

    override fun getItemCount() = menu.size

}