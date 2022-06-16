package org.d3if2033.kasirsederhana.ui.kasir

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentKasirMenuBinding
import org.d3if2033.kasirsederhana.db.HistoriEntity
import org.d3if2033.kasirsederhana.db.KasirDb
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: FragmentKasirMenuBinding;

class KasirMenuFragment : Fragment() {

    var total = 0

    private val viewModel: KasirViewModel by lazy {
        val db = KasirDb.getInstance(requireContext())
        val factory = KasirViewModelFactory(db.dao)
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
        binding = FragmentKasirMenuBinding.inflate(layoutInflater, container, false);
        setHasOptionsMenu(true)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        binding.chkSateAyam.setOnClickListener { chooseSateAyam() }
        binding.chkAyamGoreng.setOnClickListener { chooseAyamGoreng() }
        binding.chkNasi.setOnClickListener { chooseNasi() }
        binding.button.setOnClickListener { submitEvent() }
        binding.resetButton.setOnClickListener { reset() }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_kasirMenuFragment_to_historiFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(R.id.action_kasirMenuFragment_to_aboutFragment)
            return true
        }
        if (item.itemId == R.id.menu_datar_kasir) {
            findNavController().navigate(R.id.action_kasirMenuFragment_to_daftarKasirFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun submitEvent() {
        if(binding.chkAyamGoreng.isChecked) {
            if (TextUtils.isEmpty(binding.textQtyAyam.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            viewModel.hitungTotal(15000,Integer.parseInt(binding.textQtyAyam.text.toString()));
        }
        if (binding.chkSateAyam.isChecked) {
            if (TextUtils.isEmpty(binding.textQtySate.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            viewModel.hitungTotal(20000, Integer.parseInt(binding.textQtySate.text.toString()))
//            total += (20000 * Integer.parseInt(binding.textQtySate.text.toString()));
        }
        if (binding.chkNasi.isChecked) {
            if (TextUtils.isEmpty(binding.textQtyNasi.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            viewModel.hitungTotal(5000, Integer.parseInt(binding.textQtyNasi.text.toString()))
        }
        viewModel.getQuantity.observe(viewLifecycleOwner){
            if(it!=null) {
                total = it
            }
        }
        binding.totalAwal.text = getString(R.string.total, total);

        //save data ke database
        viewModel.saveHistori(
            total.toString()
        );
        Toast.makeText(requireContext(), "Data Berhasil Di Tambahkan", Toast.LENGTH_LONG).show();

        total = 0;
    }

    private fun chooseSateAyam() {
        if (binding.chkSateAyam.isChecked) {
            binding.qtySate.visibility = View.VISIBLE;
        } else {
            binding.qtySate.visibility = View.GONE;
        }
    }

    private fun chooseAyamGoreng() {
        if (binding.chkAyamGoreng.isChecked) {
            binding.qtyAyam.visibility = View.VISIBLE;
        } else {
            binding.qtyAyam.visibility = View.GONE;
        }
    }

    private fun chooseNasi() {
        if (binding.chkNasi.isChecked) {
            binding.qtyNasi.visibility = View.VISIBLE;
        } else {
            binding.qtyNasi.visibility = View.GONE;
        }
    }

    private fun reset() {
        total = 0;
        binding.qtyNasi.visibility = View.GONE;
        binding.qtyAyam.visibility = View.GONE;
        binding.qtySate.visibility = View.GONE;
        binding.chkSateAyam.setChecked(false);
        binding.chkAyamGoreng.setChecked(false);
        binding.chkNasi.setChecked(false);
        binding.totalAwal.text = getString(R.string.total, total);
    }

}