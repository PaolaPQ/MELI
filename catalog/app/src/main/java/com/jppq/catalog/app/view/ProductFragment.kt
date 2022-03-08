package com.jppq.catalog.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jppq.catalog.R
import com.jppq.catalog.app.api.service.SearchServiceImpl
import com.jppq.catalog.app.extensions.getViewModel
import com.jppq.catalog.app.extensions.loadImage
import com.jppq.catalog.app.extensions.priceFormat
import com.jppq.catalog.app.extensions.toGone
import com.jppq.catalog.app.model.SearchModelImpl
import com.jppq.catalog.app.utils.autoCleared
import com.jppq.catalog.app.view.model.ProductDeatil
import com.jppq.catalog.app.viewmodel.*
import com.jppq.catalog.databinding.FragmentProductBinding
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ProductFragment : Fragment() {

    private var binding by autoCleared<FragmentProductBinding>()
    private val args by navArgs<ProductFragmentArgs>()

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
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productResponse.observe(viewLifecycleOwner, {
            when (it) {
                is SuccessResponse -> {
                    viewModel.hideLoading()
                    setupView(it.body)
                }
                is EmptyResponse,
                is ErrorResponse -> {
                    viewModel.hideLoading()
                    findNavController().navigate(
                        ProductFragmentDirections.toError()
                    )
                }
            }
        })

        viewModel.getProduct(args.productId)
    }

    private fun setupView(productDeatil: ProductDeatil) {
        binding.apply {
            productFootnote.text = getString(
                R.string.product_footnote,
                productDeatil.condition,
                productDeatil.soldQuantity
            )
            productTitle.text = productDeatil.title
            productImage.loadImage(
                productDeatil.thumbnail,
                requireContext(),
                R.drawable.ic_loading_image,
                R.drawable.ic_no_image
            )
            productNewPrice.text = productDeatil.currentPrice.priceFormat()
            setupOff(productDeatil.originalPrice, productDeatil.currentPrice)
        }
    }

    private fun setupOff(originalPrice: Double, currentPrice: Double) {
        binding.apply {
            if (originalPrice <= 0) {
                productOldPrice.toGone()
                productOff.toGone()
            } else {
                productOldPrice.text = originalPrice.priceFormat()
                productOff.text = getString(
                    R.string.product_off,
                    calculateOff(originalPrice, currentPrice).toInt()
                )
            }
        }
    }

    private fun calculateOff(originalPrice: Double, currentPrice: Double) =
        (1 - (currentPrice / originalPrice)) * 100
}