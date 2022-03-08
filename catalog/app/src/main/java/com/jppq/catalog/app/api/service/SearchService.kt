package com.jppq.catalog.app.api.service

import com.jppq.catalog.app.api.model.ApiResponse
import com.jppq.catalog.app.api.model.ProductDetailApiModel
import com.jppq.catalog.app.api.model.SearchApiModel

interface SearchService {

    suspend fun searchByText(text: String): ApiResponse<SearchApiModel>
    suspend fun getProduct(productId: String): ApiResponse<ProductDetailApiModel>
    suspend fun getProductDetail(productId: String): ApiResponse<Any>
}