package org.d3if2033.kasirsederhana.ui.tambahmenu

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentTambahMenuBinding
import org.d3if2033.kasirsederhana.db.MenuDb

class TambahMenuFragment : Fragment() {

    private lateinit var binding: FragmentTambahMenuBinding;

    private val viewModel: TambahMenuViewModel by lazy {
        val db = MenuDb.getInstance(requireContext());
        val factory = TambahViewModelFactory(db.dao);
        ViewModelProvider(this, factory)[TambahMenuViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTambahMenuBinding.inflate(layoutInflater, container, false);
        setHasOptionsMenu(true)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener { tambahMenuBaru() }
        binding.buttonReset.setOnClickListener { reset() }
    }

    private fun tambahMenuBaru() {
        val nama = binding.namaMenu.text.toString();
        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(context, R.string.nama_menu_invalid, Toast.LENGTH_LONG).show();
            return
        }

        val deskripsi = binding.deskripsi.text.toString();
        if (TextUtils.isEmpty(deskripsi)) {
            Toast.makeText(context, R.string.deskripsi_invalid, Toast.LENGTH_LONG).show();
            return
        }

        val harga = binding.harga.text.toString();
        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(context, R.string.harga_invalid, Toast.LENGTH_LONG)
        }

        viewModel.insertNewMenu(
            nama.toString(),
            deskripsi.toString(),
            harga.toString()
        )
        Toast.makeText(requireContext(), "Data Berhasil Di Tambahkan", Toast.LENGTH_LONG).show();
    }

    private fun reset() {
        binding.namaMenu.setText("");
        binding.deskripsi.setText("");
        binding.harga.setText("");
    }
}