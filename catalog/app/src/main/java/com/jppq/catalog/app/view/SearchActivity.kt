package com.jppq.catalog.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.jppq.catalog.R
import com.jppq.catalog.app.api.service.SearchServiceImpl
import com.jppq.catalog.app.extensions.getViewModel
import com.jppq.catalog.app.extensions.toGone
import com.jppq.catalog.app.extensions.toVisible
import com.jppq.catalog.app.model.SearchModelImpl
import com.jppq.catalog.app.viewmodel.SearchViewModel
import com.jppq.catalog.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    val viewModel: SearchViewModel by lazy {
        getViewModel {
            SearchViewModel(
                SearchModelImpl(SearchServiceImpl())
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel.loading.observe(this, {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })

        setContentView(binding.root)
    }

    private fun showProgressBar() {
        binding.apply {
            navHost.toGone()
            progressBar.toVisible()
        }
    }

    private fun hideProgressBar() {
        binding.apply {
            progressBar.toGone()
            navHost.toVisible()
        }
    }
}