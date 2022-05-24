package org.d3if2033.kasirsederhana.ui.histori

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentHistoriBinding
import org.d3if2033.kasirsederhana.db.KasirDb
import org.d3if2033.kasirsederhana.ui.kasir.KasirViewModel
import java.text.SimpleDateFormat
import java.util.*

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
        setupOnItemClick()

        viewModel.data.observe(viewLifecycleOwner, { menu ->
            Log.d("TesData", menu.toString())
            myAdapter.submitList(menu)
        })

        binding.floatingActionButton2.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(R.string.konfirmasi_hapus)
                .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                    viewModel.hapusData()
                    Toast.makeText(requireContext(), "Data Berhasil Di Hapus", Toast.LENGTH_LONG).show();
                }
                .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }

    }

    private fun setupOnItemClick() {
        myAdapter.onItemClick = { data ->
            val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID"))
            var tanggal = dateFormatter.format(data.tanggalPembelian).toString()
            val message = getString(R.string.bagikan_template, tanggal, data.total.toInt())
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
            if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(shareIntent)
            }
        }

        myAdapter.onItemDelete = {

        }
    }
}