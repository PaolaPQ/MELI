package com.jppq.catalog.app.converter

import com.jppq.catalog.app.api.model.ProductDetailApiModel
import com.jppq.catalog.app.view.model.ProductDeatil
import java.math.BigDecimal

class ProductDetailConverter {

    companion object {

        fun convertToProductDetail(
            data: ProductDetailApiModel
        ) = ProductDeatil (
            data.title,
            data.currentPrice,
            data.originalPrice ?: 0.0,
            data.currencyId,
            data.soldQuantity,
            data.thumbnail,
            data.condition
        )
    }
}