package org.d3if2033.kasirsederhana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.d3if2033.kasirsederhana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    var total: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.chkAyamGoreng.setOnClickListener { chooseAyamGoreng() }
        binding.chkSateAyam.setOnClickListener { chooseSateAyam() }
        binding.chkNasi.setOnClickListener { chooseNasi() }
        binding.button.setOnClickListener { submitEvent() }

    }

    fun submitEvent() {
        if(binding.chkAyamGoreng.isChecked) {
            total += (15000 * Integer.parseInt(binding.textQtyAyam.text.toString()));
        }
        if (binding.chkSateAyam.isChecked) {
            total += (20000 * Integer.parseInt(binding.textQtySate.text.toString()));
        }
        if (binding.chkNasi.isChecked) {
            total += (5000 * Integer.parseInt(binding.textQtyNasi.text.toString()));
        }
        binding.totalAwal.text = getString(R.string.total, total);
    }

    fun chooseSateAyam() {
        if (binding.chkSateAyam.isChecked) {
            binding.qtySate.visibility = View.VISIBLE;
        } else {
            binding.qtySate.visibility = View.GONE;
        }
    }

    fun chooseAyamGoreng() {
        if (binding.chkAyamGoreng.isChecked) {
            binding.qtyAyam.visibility = View.VISIBLE;
        } else {
            binding.qtyAyam.visibility = View.GONE;
        }

    }

    fun chooseNasi() {
        if (binding.chkNasi.isChecked) {
            binding.qtyNasi.visibility = View.VISIBLE;
        } else {
            binding.qtyNasi.visibility = View.GONE;
        }
    }

    fun reset() {
        total = 0;
        binding.qtyNasi.visibility = View.GONE;
        binding.qtyAyam.visibility = View.GONE;
        binding.qtySate.visibility = View.GONE;
    }
}