package com.jppq.catalog.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jppq.catalog.app.api.service.SearchServiceImpl
import com.jppq.catalog.app.extensions.getViewModel
import com.jppq.catalog.app.model.SearchModelImpl
import com.jppq.catalog.app.utils.autoCleared
import com.jppq.catalog.app.view.adapters.ProductAdapter
import com.jppq.catalog.app.view.model.Search
import com.jppq.catalog.app.viewmodel.*
import com.jppq.catalog.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {

    private var binding by autoCleared<FragmentSearchResultBinding>()
    private val args by navArgs<SearchResultFragmentArgs>()

    val viewModel: SearchViewModel by lazy {
        getViewModel(requireActivity()) {
            SearchViewModel(
                SearchModelImpl(SearchServiceImpl())
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchResponse.observe(viewLifecycleOwner, {
            when (it) {
                is SuccessResponse -> {
                    viewModel.hideLoading()
                    setupResult(it.body)
                }
                is EmptyResponse,
                is ErrorResponse -> {
                    viewModel.hideLoading()
                    goToErrorView()
                }
            }
        })

        viewModel.searchByText(args.textToSearch)
    }

    fun setupResult(data: Search) {
        if (data.results.isEmpty()) {
            goToErrorView()
        } else {
            binding.resultList.layoutManager =
                LinearLayoutManager(this.requireActivity())

            binding.resultList.adapter =
                ProductAdapter(
                    this.requireContext(),
                    data.results
                ) { selectedProduct ->
                    findNavController().navigate(
                        SearchResultFragmentDirections.toProductDetail(
                            selectedProduct.id
                        )
                    )
                }
        }
    }

    fun goToErrorView() {
        findNavController().navigate(
            SearchResultFragmentDirections.toError()
        )
    }
}