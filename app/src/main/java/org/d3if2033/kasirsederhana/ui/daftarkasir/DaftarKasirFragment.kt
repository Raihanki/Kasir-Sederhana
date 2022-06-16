package org.d3if2033.kasirsederhana.ui.daftarkasir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentDaftarKasirBinding
import org.d3if2033.kasirsederhana.db.KasirDb
import org.d3if2033.kasirsederhana.ui.histori.HistoriViewModel
import org.d3if2033.kasirsederhana.ui.histori.HistoriViewModelFactory

class DaftarKasirFragment : Fragment() {

    private lateinit var binding: FragmentDaftarKasirBinding;

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
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData();
    }
}