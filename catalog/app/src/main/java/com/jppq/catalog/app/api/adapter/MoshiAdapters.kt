package com.jppq.catalog.app.api.adapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class MoshiAdapters {

    companion object {

        val converterFactory: MoshiConverterFactory by lazy {
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .add(BigDecimalAdapter())
                    .build()
            )
        }
    }
}