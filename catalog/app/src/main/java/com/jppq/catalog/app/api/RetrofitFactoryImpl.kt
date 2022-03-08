package com.jppq.catalog.app.api

import com.jppq.catalog.core.RetrofitFactory
import okhttp3.OkHttpClient
import retrofit2.Converter

class RetrofitFactoryImpl: RetrofitFactory() {
    override fun initializateRetrofit(converterFactory: Converter.Factory, client: OkHttpClient) {
        setServiceRetrofit(converterFactory, client)
    }
}