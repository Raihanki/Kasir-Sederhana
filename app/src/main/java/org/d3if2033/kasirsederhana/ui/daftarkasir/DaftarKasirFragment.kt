package org.d3if2033.kasirsederhana.ui.daftarkasir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentDaftarKasirBinding
import org.d3if2033.kasirsederhana.db.KasirDb
import org.d3if2033.kasirsederhana.network.ApiStatus
import org.d3if2033.kasirsederhana.ui.histori.HistoriViewModel
import org.d3if2033.kasirsederhana.ui.histori.HistoriViewModelFactory

class DaftarKasirFragment : Fragment() {

    private lateinit var binding: FragmentDaftarKasirBinding;
    private lateinit var myAdapter: DaftarKasirAdapter;

    private val viewModel: DaftarKasirViewModel by lazy {
        ViewModelProvider(this).get(DaftarKasirViewModel::class.java);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaftarKasirBinding.inflate(layoutInflater, container, false);
        myAdapter = DaftarKasirAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true);
        }
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it);
        })

        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })

        viewModel.scheduleUpdater(requireActivity().application);
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE;
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE;
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE;
                binding.networkError.visibility = View.VISIBLE;
            }
        }
    }
}