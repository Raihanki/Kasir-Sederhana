package org.d3if2033.kasirsederhana.ui.histori

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2033.kasirsederhana.databinding.ItemHistoriBinding
import org.d3if2033.kasirsederhana.db.HistoriEntity
import org.d3if2033.kasirsederhana.model.Histori
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter<HistoriEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    lateinit var onItemClick: ((HistoriEntity) -> Unit)
    lateinit var onItemDelete: ((HistoriEntity) -> Unit)

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<HistoriEntity>() {
                override fun areItemsTheSame(
                    oldData: HistoriEntity, newData: HistoriEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: HistoriEntity, newData: HistoriEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    inner class ViewHolder(private val binding: ItemHistoriBinding): RecyclerView.ViewHolder(binding.root)
    {
        @SuppressLint("SetTextI18n")
        fun bind(item: HistoriEntity) = with(binding) {
            val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
                Locale("id", "ID"))

            tanggalTransaksi.text = "Pembelian pada tanggal " + dateFormatter.format(Date(item.tanggalPembelian))
            total.text = "Total Harga : Rp. " + item.total

            binding.shareButton.setOnClickListener{
                onItemClick(item)
            }

            binding.deleteButton.setOnClickListener {
                onItemDelete(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val binding = ItemHistoriBinding.inflate(inflater, parent, false);
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}