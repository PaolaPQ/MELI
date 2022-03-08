package com.jppq.catalog.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jppq.catalog.app.converter.ProductDetailConverter
import com.jppq.catalog.app.converter.SearchConverter
import com.jppq.catalog.app.extensions.parse
import com.jppq.catalog.app.model.SearchModel
import com.jppq.catalog.app.view.model.ProductDeatil
import com.jppq.catalog.app.view.model.Search
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class SearchViewModel(
    private val model: SearchModel
): ViewModel(), CoroutineScope {

    override val coroutineContext = Job()

    private var loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var searchResponseLiveData: MutableLiveData<ViewModelResponse<Search>> = MutableLiveData()
    private var productResponseLiveData: MutableLiveData<ViewModelResponse<ProductDeatil>> = MutableLiveData()

    val loading: LiveData<Boolean>
        get() = loadingLiveData

    val searchResponse: LiveData<ViewModelResponse<Search>>
        get() = searchResponseLiveData

    val productResponse: LiveData<ViewModelResponse<ProductDeatil>>
        get() = productResponseLiveData

    fun showLoading() {
        loadingLiveData.postValue(true)
    }

    fun hideLoading(){
        loadingLiveData.postValue(false)
    }

    fun searchByText(text: String) {
        launch {
            showLoading()
            searchResponseLiveData.postValue(
                model.searchByText(text).parse {
                    return@parse SearchConverter.convertToSearchResult(it)
                }
            )
        }
    }

    fun getProduct(productId: String) {
        launch {
            showLoading()
            productResponseLiveData.postValue(
                model.getProduct(productId).parse {
                    return@parse ProductDetailConverter.convertToProductDetail(it)
                }
            )
        }
    }
}