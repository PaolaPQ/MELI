package com.jppq.catalog.app.api.service

import com.jppq.catalog.app.api.model.ApiResponse
import com.jppq.catalog.app.api.model.ProductDetailApiModel
import com.jppq.catalog.app.api.model.SearchApiModel
import com.jppq.catalog.app.extensions.parse
import com.jppq.catalog.core.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchServiceImpl: BaseService(), SearchService {

    private val homeCatitApi: SearchApi by lazy {
        RetrofitFactory.getServiceRetrofit(SearchApi::class.java)
    }

    override suspend fun searchByText(text: String): ApiResponse<SearchApiModel> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext homeCatitApi.searchByText(text).parse()
            } catch (e: Exception) {
                return@withContext getError(e)
            }
        }
    }

    override suspend fun getProduct(productId: String): ApiResponse<ProductDetailApiModel> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext homeCatitApi.getProduct(productId).parse()
            } catch (e: Exception) {
                return@withContext getError(e)
            }
        }
    }

    override suspend fun getProductDetail(productId: String): ApiResponse<Any> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext homeCatitApi.getProductDetail(productId).parse()
            } catch (e: Exception) {
                return@withContext getError(e)
            }
        }
    }
}