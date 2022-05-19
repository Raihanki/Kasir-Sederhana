package org.d3if2033.kasirsederhana.ui.detailmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentDetailMenuBinding
import org.d3if2033.kasirsederhana.ui.detailmenu.model.DetailMenu
import org.d3if2033.kasirsederhana.ui.kasir.model.Menu

private lateinit var binding: FragmentDetailMenuBinding

class DetailMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailMenuBinding.inflate(layoutInflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataToFragment()
    }

    private fun getData(): List<DetailMenu> {
        return listOf(
            DetailMenu("Ayam Goreng", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor facilis illum quam saepe veritatis", "50000"),
            DetailMenu("Nasi Goreng", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor facilis illum quam saepe veritatis","30000"),
            DetailMenu("Pempek Palembang", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor facilis illum quam saepe veritatis","20000")
        )
    }

    private fun setDataToFragment() {
        val data = getData();
        for(i in data) {
            binding.textView.text = i.nama;
            binding.textDeskripsi.text = i.deskripsi;
            binding.textHarga.text = i.harga
        }
    }

}