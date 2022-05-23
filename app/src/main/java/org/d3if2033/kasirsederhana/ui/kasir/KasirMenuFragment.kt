package org.d3if2033.kasirsederhana.ui.kasir

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.databinding.FragmentKasirMenuBinding

private lateinit var binding: FragmentKasirMenuBinding;

class KasirMenuFragment : Fragment() {

    var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKasirMenuBinding.inflate(layoutInflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chkSateAyam.setOnClickListener { chooseSateAyam() }
        binding.chkNasi.setOnClickListener { chooseNasi() }
        binding.button.setOnClickListener { submitEvent() }
        binding.resetButton.setOnClickListener { reset() }
    }

    private fun submitEvent() {
        if(binding.chkAyamGoreng.isChecked) {
            if (TextUtils.isEmpty(binding.textQtyAyam.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            total += (15000 * Integer.parseInt(binding.textQtyAyam.text.toString()));
        }
        if (binding.chkSateAyam.isChecked) {
            if (TextUtils.isEmpty(binding.textQtySate.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            total += (20000 * Integer.parseInt(binding.textQtySate.text.toString()));
        }
        if (binding.chkNasi.isChecked) {
            if (TextUtils.isEmpty(binding.textQtyNasi.text.toString())) {
                Toast.makeText(context, R.string.invalid_feedback, Toast.LENGTH_LONG).show()
                return
            }
            total += (5000 * Integer.parseInt(binding.textQtyNasi.text.toString()));
        }
        binding.totalAwal.text = getString(R.string.total, total);
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