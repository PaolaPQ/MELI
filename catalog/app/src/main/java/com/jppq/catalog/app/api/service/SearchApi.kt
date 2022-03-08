package com.jppq.catalog.app.api.service

import com.jppq.catalog.app.api.model.ProductDetailApiModel
import com.jppq.catalog.app.api.model.SearchApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {

    @GET("sites/MLA/search")
    suspend fun searchByText(
        @Query("q") text: String
    ): Response<SearchApiModel>

    @GET("items/{product_id}")
    suspend fun getProduct(
        @Path("product_id") text: String
    ): Response<ProductDetailApiModel>

    @GET("items/{product_id}/description")
    suspend fun getProductDetail(
        @Path("product_id") text: String
    ): Response<Any>
}