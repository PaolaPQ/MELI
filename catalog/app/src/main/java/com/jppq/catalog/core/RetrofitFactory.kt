package com.jppq.catalog.core

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

abstract class RetrofitFactory {

    companion object {

        private lateinit var client: OkHttpClient
        private lateinit var converterFactory: Converter.Factory

        fun setServiceRetrofit(converterFactory: Converter.Factory, client: OkHttpClient) {
            Companion.client = client
            Companion.converterFactory = converterFactory
        }

        private val instance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Config.baseUrl)
                .addConverterFactory(converterFactory)
                .client(client)
                .build()
        }

        fun <T> getServiceRetrofit(service: Class<T>): T = instance.create(service)
    }

    abstract fun initializateRetrofit(converterFactory: Converter.Factory, client: OkHttpClient)
}