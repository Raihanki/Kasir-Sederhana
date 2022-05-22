package org.d3if2033.kasirsederhana.ui.kasir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.ListMenuBinding
import org.d3if2033.kasirsederhana.db.MenuEntity
import org.d3if2033.kasirsederhana.ui.kasir.model.Menu

//class KasirAdapter(private val menu : List<Menu>) : RecyclerView.Adapter<KasirAdapter.KasirViewHolder>() {

//class KasirAdapter: RecyclerView.Adapter<KasirAdapter.KasirViewHolder>() {
//
//    private var listMenu = List<MenuEntity>()
//
//    class KasirViewHolder(val binding: ListMenuBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KasirViewHolder {
//        val inflater = LayoutInflater.from(parent.context);
//        val binding = ListMenuBinding.inflate(inflater, parent, false);
//        return KasirViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: KasirViewHolder, position: Int) {
//        val i = listMenu[position]
//        holder.binding.chkMenu.text = i.nama;
//        holder.binding.txtHarga.text = i.harga;
//    }
//
//    override fun getItemCount() = listMenu.size

//    fun setData(menu: MenuEntity?) {
//        this.listMenu = menu
//        notifyDataSetChanged()
//    }

//}


class KasirAdapter :
    ListAdapter<MenuEntity, KasirAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<MenuEntity>() {
                override fun areItemsTheSame(
                    oldData: MenuEntity, newData: MenuEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: MenuEntity, newData: MenuEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    class ViewHolder(
        private val binding: ListMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuEntity) = with(binding) {
            chkMenu.text = item.nama;
            txtHarga.text = item.harga;
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListMenuBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}