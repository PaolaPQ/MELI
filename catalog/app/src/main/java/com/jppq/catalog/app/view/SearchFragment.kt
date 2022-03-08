package com.jppq.catalog.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jppq.catalog.app.utils.autoCleared
import com.jppq.catalog.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var binding by autoCleared<FragmentSearchBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            searchButton.setOnClickListener {
                findNavController().navigate(
                    SearchFragmentDirections.toSearchResult(
                        searchInput.getText()
                    )
                )
            }

            searchInput.setupView {
                enableButton()
            }

            searchInput.clearFocus()
        }
    }

    private fun enableButton() {
        binding.apply {
            searchButton.isEnabled = searchInput.isError.not()
        }
    }
}