package com.jppq.catalog.app.api

import com.jppq.catalog.core.OkHttpFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpFactoryImpl(): OkHttpFactory() {
    override fun makeOkHttpClient(): OkHttpClient =
        getOkHttpClientBuilder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            .build()
}