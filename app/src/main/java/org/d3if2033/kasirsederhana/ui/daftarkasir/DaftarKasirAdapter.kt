package org.d3if2033.kasirsederhana.ui.daftarkasir

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if2033.kasirsederhana.databinding.ItemDaftarKasirBinding
import org.d3if2033.kasirsederhana.databinding.ItemHistoriBinding
import org.d3if2033.kasirsederhana.model.Kasir
import java.text.SimpleDateFormat
import java.util.*

class DaftarKasirAdapter : RecyclerView.Adapter<DaftarKasirAdapter.ViewHolder>() {
    private val data = mutableListOf<Kasir>();
    fun updateData(newData: List<Kasir>) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemDaftarKasirBinding): RecyclerView.ViewHolder(binding.root)
    {
        @SuppressLint("SetTextI18n")
        fun bind(item: Kasir) = with(binding) {
            namaTextView.text = item.name;
            ageText.text = item.age;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val binding = ItemDaftarKasirBinding.inflate(inflater, parent, false);
        return ViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position]);
    }

    override fun getItemCount(): Int {
        return data.size;
    }
}