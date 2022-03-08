package com.jppq.catalog.app.modules

import com.jppq.catalog.core.OkHttpFactory
import com.jppq.catalog.app.api.OkHttpFactoryImpl
import com.jppq.catalog.app.api.RetrofitFactoryImpl
import com.jppq.catalog.app.api.adapter.MoshiAdapters
import okhttp3.OkHttpClient

class RetrofitModule {

    private val okHttpFactory: OkHttpFactory = OkHttpFactoryImpl()
    private val retrofitFactory = RetrofitFactoryImpl()
    val okHttpClient: OkHttpClient

    init {
        okHttpClient = okHttpFactory.makeOkHttpClient()
        retrofitFactory.initializateRetrofit(MoshiAdapters.converterFactory, okHttpClient)
    }
}