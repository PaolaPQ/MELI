package com.jppq.catalog.app.api.service

import com.jppq.catalog.app.api.model.ErrorApiResponse
import com.jppq.catalog.app.utils.ErrorConstants.Companion.GENERIC
import com.jppq.catalog.app.utils.LoggerConstants.Companion.JSON_DATA_MISSING
import com.squareup.moshi.JsonDataException
import java.net.UnknownHostException

open class BaseService {

    fun getError(e: Exception): ErrorApiResponse {
        var code = GENERIC
        var message = e.message ?: String()
        when (e) {
            is UnknownHostException -> {
                code = 0
                message = ""
            }
            is JsonDataException -> message = e.message ?: JSON_DATA_MISSING
        }

        return ErrorApiResponse(code, message)
    }
}