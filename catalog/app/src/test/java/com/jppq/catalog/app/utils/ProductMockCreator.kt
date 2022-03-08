package com.jppq.catalog.app.utils

import com.jppq.catalog.app.api.model.ProductDetailApiModel

class ProductMockCreator {

    companion object {
        
        fun createProductResponse() =
            ProductDetailApiModel(
                "Moto g6 plus",
                850000.0,
                1000000.0,
                "ARG",
                30,
                "",
                "new"
            )
    }
}