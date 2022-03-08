package com.jppq.catalog.core

import android.content.Context
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

abstract class OkHttpFactory {

    private val connectTimeout = 30L
    private val readTimeout = 30L
    private val callTimeout = 75L
    private val timeUnit = TimeUnit.SECONDS

    abstract fun makeOkHttpClient():
            OkHttpClient

    protected fun getOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .callTimeout(callTimeout, timeUnit)
            .connectTimeout(connectTimeout, timeUnit)
            .readTimeout(readTimeout, timeUnit)
}