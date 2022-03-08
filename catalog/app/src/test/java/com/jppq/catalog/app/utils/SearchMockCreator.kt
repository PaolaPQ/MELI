package com.jppq.catalog.app.utils

import com.jppq.catalog.app.api.model.ProductApiModel
import com.jppq.catalog.app.api.model.ProductDetailApiModel
import com.jppq.catalog.app.api.model.SearchApiModel

class SearchMockCreator {

    companion object {

        fun createSearchResponse() =
            SearchApiModel(
                listOf(
                    ProductApiModel(
                        "1234",
                        "MLA",
                        "Moto g6 plus",
                        850000.0,
                        "AGS",
                        2,
                        30,
                        ""
                    )
                )
            )

        fun createSearchEmptyResponse() =
            SearchApiModel(
                listOf()
            )
    }
}