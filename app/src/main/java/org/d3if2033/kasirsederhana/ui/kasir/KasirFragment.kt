package org.d3if2033.kasirsederhana.ui.kasir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentKasirBinding
import org.d3if2033.kasirsederhana.ui.kasir.model.Menu

private lateinit var binding: FragmentKasirBinding;

class KasirFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKasirBinding.inflate(layoutInflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.recycleViewMenu) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = KasirAdapter(getData())
            setHasFixedSize(true)
        }
    }

    private fun getData(): List<Menu> {
        return listOf(
            Menu("Ayam Goreng", "50000"),
            Menu("Nasi Goreng", "30000"),
            Menu("Pempek Palembang", "20000")
        )
    }

}