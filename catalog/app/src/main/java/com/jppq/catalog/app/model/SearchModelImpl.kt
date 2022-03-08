package com.jppq.catalog.app.model

import com.jppq.catalog.app.api.model.ApiResponse
import com.jppq.catalog.app.api.model.ProductDetailApiModel
import com.jppq.catalog.app.api.model.SearchApiModel
import com.jppq.catalog.app.api.service.SearchService

class SearchModelImpl(
    private val service: SearchService
): SearchModel {

    override suspend fun searchByText(text: String): ApiResponse<SearchApiModel> {
        return service.searchByText(text)
    }

    override suspend fun getProduct(productId: String): ApiResponse<ProductDetailApiModel> {
        return service.getProduct(productId)
    }

    override suspend fun getProductDetail(productId: String): ApiResponse<Any> {
        return service.getProductDetail(productId)
    }
}