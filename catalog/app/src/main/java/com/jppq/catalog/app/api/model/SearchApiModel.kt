package com.jppq.catalog.app.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class SearchApiModel(
    val results: List<ProductApiModel>,
)

@JsonClass(generateAdapter = true)
data class ProductApiModel(
    val id: String,
    @Json(name = "site_id")
    val siteId: String,
    val title: String,
    val price: Double,
    @Json(name = "currency_id")
    val currencyId: String,
    @Json(name = "available_quantity")
    val availableQuantity: Int,
    @Json(name = "sold_quantity")
    val soldQuantity: Int,
    val thumbnail: String
)

@JsonClass(generateAdapter = true)
data class ProductDetailApiModel(
    val title: String,
    @Json(name = "price")
    val currentPrice: Double,
    @Json(name = "original_price")
    val originalPrice: Double?,
    @Json(name = "currency_id")
    val currencyId: String,
    @Json(name = "sold_quantity")
    val soldQuantity: Int,
    val thumbnail: String,
    val condition: String
)