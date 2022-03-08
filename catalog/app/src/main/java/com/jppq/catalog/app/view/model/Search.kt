package com.jppq.catalog.app.view.model

import java.math.BigDecimal


data class Search(
    val results: List<Product>,
)

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
)

data class ProductDeatil(
    val title: String,
    val currentPrice: Double,
    val originalPrice: Double,
    val currencyId: String,
    val soldQuantity: Int,
    val thumbnail: String,
    val condition: String
)