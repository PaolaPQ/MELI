package com.jppq.catalog.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jppq.catalog.R
import com.jppq.catalog.app.utils.autoCleared
import com.jppq.catalog.databinding.FragmentSearchErrorBinding
import com.jppq.catalog.databinding.FragmentSearchResultBinding

class SearchErrorFragment : Fragment() {

    private var binding by autoCleared<FragmentSearchErrorBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchErrorBinding.inflate(inflater, container, false)
        return binding.root
    }
}