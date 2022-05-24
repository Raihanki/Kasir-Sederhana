package org.d3if2033.kasirsederhana.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.d3if2033.kasirsederhana.R
import org.d3if2033.kasirsederhana.data.SettingDataStore
import org.d3if2033.kasirsederhana.data.dataStore
import org.d3if2033.kasirsederhana.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private var isTextNormal: Boolean = true
    private lateinit var binding: FragmentAboutBinding
    private lateinit var textDataStore: SettingDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textDataStore = SettingDataStore(requireContext().dataStore)
        textDataStore.preferenceFlow.asLiveData()
            .observe(viewLifecycleOwner, { value ->
                isTextNormal = value
                chooseText()
                activity?.invalidateOptionsMenu()
            })

        binding.floatingActionButton3.setOnClickListener {
            isTextNormal = !isTextNormal
            lifecycleScope.launch {
                textDataStore.saveLayoutToPreferencesStore(
                    isTextNormal, requireContext()
                )
            }
            chooseText()
        }
    }

    private fun chooseText() {
        if (isTextNormal) {
            binding.textView5.visibility = View.VISIBLE
            binding.textNormal.visibility = View.VISIBLE
            binding.textJudulMerah.visibility = View.GONE
            binding.textBiru.visibility = View.GONE
        } else {
            binding.textView5.visibility = View.GONE
            binding.textNormal.visibility = View.GONE
            binding.textJudulMerah.visibility = View.VISIBLE
            binding.textBiru.visibility = View.VISIBLE
        }
    }
}