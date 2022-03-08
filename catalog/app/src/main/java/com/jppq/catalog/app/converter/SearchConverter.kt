package com.jppq.catalog.app.converter

import com.jppq.catalog.app.api.model.SearchApiModel
import com.jppq.catalog.app.view.model.Product
import com.jppq.catalog.app.view.model.Search

class SearchConverter {

    companion object {

        fun convertToSearchResult(
            data: SearchApiModel
        ): Search {
            return Search(
                data.results.map {
                    Product(
                        it.id,
                        it.title,
                        it.price,
                        it.thumbnail
                    )
                }
            )
        }
    }
}