package com.jppq.catalog.app.extensions

import com.jppq.catalog.app.api.model.ApiResponse
import com.jppq.catalog.app.api.model.EmptyApiResponse
import com.jppq.catalog.app.api.model.ErrorApiResponse
import com.jppq.catalog.app.api.model.SuccessApiResponse
import retrofit2.Response

/**
 * This extension function replace the NetworkBound
 * so given a Response<Data> evaluate the status code
 * and returns an ApiResponse<Data>
 */
fun <Data> Response<Data>.parse(): ApiResponse<Data> {
    if (this.isSuccessful) {
        return when (this.code()) {
            200 -> {
                this.body()?.let { data ->
                    SuccessApiResponse(data)
                } ?: run {
                    EmptyApiResponse()
                }
            }
            204 -> {
                EmptyApiResponse()
            }
            else -> {
                EmptyApiResponse()
            }
        }
    } else {
        val message = errorBody()?.string() ?: "unknown error"
        return ErrorApiResponse(code(), message)
    }
}