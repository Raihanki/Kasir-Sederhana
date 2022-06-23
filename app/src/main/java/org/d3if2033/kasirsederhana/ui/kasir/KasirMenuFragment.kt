package org.d3if2033.kasirsederhana.ui.kasir

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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
        binding.chkPempekLenggang.setOnClickListener { choosePempekLenggang() }
        binding.chkPempekAdaan.setOnClickListener { choosePempekAdaan() }
        binding.chkPempekLenjerBesar.setOnClickListener { chooseLenjerBesar() }
        binding.button.setOnClickListener { submitEvent() }
        binding.resetButton.setOnClickListener { resetTotal() }

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
        if(binding.chkPempekLenggang.isChecked) {
            if (TextUtils.isEmpty(binding.textQtyLenggang.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            viewModel.hitungTotal(13000,Integer.parseInt(binding.textQtyLenggang.text.toString()));
            viewModel.getQuantity.observeOnce(viewLifecycleOwner){
                if(it!=null) {
                    Log.d("total", "sbl dtmbh $total")
                    total += it
                    Log.d("total", "sbdh dtmbh $total")
                }
            }
        }
        if (binding.chkPempekLenjerBesar.isChecked) {
            if (TextUtils.isEmpty(binding.textQtyLenjerBesar.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            viewModel.hitungTotal(11000, Integer.parseInt(binding.textQtyLenjerBesar.text.toString()))
            viewModel.getQuantity.observeOnce(viewLifecycleOwner){
                if(it!=null) {
                    Log.d("total", "sbl dtmbh $total")
                    total += it
                    Log.d("total", "sbdh dtmbh $total")
                }
            }
        }
        if (binding.chkPempekAdaan.isChecked) {
            if (TextUtils.isEmpty(binding.textQtyAdaan.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            viewModel.hitungTotal(6000, Integer.parseInt(binding.textQtyAdaan.text.toString()))
            viewModel.getQuantity.observeOnce(viewLifecycleOwner){
                if(it!=null) {
                    Log.d("total", "sbl dtmbh $total")
                    total += it
                    Log.d("total", "sbdh dtmbh $total")
                }
            }
        }
        binding.totalAwal.text = getString(R.string.total, total);

        //save data ke database
        viewModel.saveHistori(
            total.toString()
        );
        Toast.makeText(requireContext(), "Data Berhasil Di Tambahkan", Toast.LENGTH_LONG).show();

        total = 0;
        reset()
    }

    private fun choosePempekLenggang() {
        if (binding.chkPempekLenggang.isChecked) {
            binding.qtyLenggang.visibility = View.VISIBLE;
        } else {
            binding.qtyLenggang.visibility = View.GONE;
        }
    }

    private fun choosePempekAdaan() {
        if (binding.chkPempekAdaan.isChecked) {
            binding.qtyAdaan.visibility = View.VISIBLE;
        } else {
            binding.qtyAdaan.visibility = View.GONE;
        }
    }

    private fun chooseLenjerBesar() {
        if (binding.chkPempekLenjerBesar.isChecked) {
            binding.qtyLenjerBesar.visibility = View.VISIBLE;
        } else {
            binding.qtyLenjerBesar.visibility = View.GONE;
        }
    }

    private fun reset() {
        total = 0;
        binding.qtyLenjerBesar.visibility = View.GONE;
        binding.qtyLenggang.visibility = View.GONE;
        binding.qtyAdaan.visibility = View.GONE;
        binding.textQtyAdaan.text?.clear()
        binding.textQtyLenggang.text?.clear()
        binding.textQtyLenjerBesar.text?.clear()
        binding.chkPempekLenjerBesar.setChecked(false);
        binding.chkPempekAdaan.setChecked(false);
        binding.chkPempekLenggang.setChecked(false);
        viewModel.quantity.value = 0
    }

    private fun resetTotal()
    {
        reset();
        binding.totalAwal.text = getString(R.string.total, total);
    }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }
}