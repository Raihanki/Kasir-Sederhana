package org.d3if2033.kasirsederhana.ui.kasir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentKasirBinding
import org.d3if2033.kasirsederhana.db.MenuDb
import org.d3if2033.kasirsederhana.db.MenuEntity
import org.d3if2033.kasirsederhana.ui.kasir.model.Menu


class KasirFragment : Fragment() {

    private lateinit var binding: FragmentKasirBinding;
    private lateinit var myAdapter: KasirAdapter;

    private val viewModel: KasirViewModel by lazy {
        val db = MenuDb.getInstance(requireContext())
        val factory = KasirViewModelFactory(db.dao);
        ViewModelProvider(this, factory)[KasirViewModel::class.java]
    }

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

        myAdapter = KasirAdapter()
        with(binding.recycleViewMenu) {
            adapter = myAdapter
        }

        viewModel.data.observe(viewLifecycleOwner, { menu ->
            myAdapter.submitList(menu)
        })

        setupOnItemClick()

        setupObservers()

        binding.buttonTambah.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_kasirFragment_to_tambahMenuFragment
            )
        }
    }

    private fun setupObservers() {
        viewModel.getDataMenu.observe(viewLifecycleOwner){
            if (it != null) {
                showTotal(it)
            }
        }
    }

    private fun showTotal(list: ArrayList<MenuEntity>) {
//        binding.totalAwal.text = list
    }

    private fun setupOnItemClick() {
        myAdapter.setListener(object : KasirAdapter.KasirListener {
            override fun onItemClick(dataMenu: MenuEntity, isChecked: Boolean, qty: String) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getData(): List<Menu> {
        return listOf(
            Menu("Ayam Goreng", "50000"),
            Menu("Nasi Goreng", "30000"),
            Menu("Pempek Palembang", "20000")
        )
    }

    private fun submitEvent() {
    }


}