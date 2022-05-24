package org.d3if2033.kasirsederhana.ui.histori

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2033.kasirsederhana.databinding.FragmentHistoriBinding
import org.d3if2033.kasirsederhana.db.KasirDb
import org.d3if2033.kasirsederhana.ui.kasir.KasirViewModel

class HistoriFragment : Fragment() {

    private lateinit var binding: FragmentHistoriBinding;
    private lateinit var myAdapter: HistoriAdapter

    private val viewModel: HistoriViewModel by lazy {
        val db = KasirDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoriBinding.inflate(layoutInflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)

        myAdapter = HistoriAdapter()
        with(binding.recyclerView) {
            adapter = myAdapter
        }

        viewModel.data.observe(viewLifecycleOwner, { menu ->
            Log.d("TesData", menu.toString())
            myAdapter.submitList(menu)
        })

    }
}