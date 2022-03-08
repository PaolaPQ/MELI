package com.jppq.catalog.app.extensions

import com.jppq.catalog.app.api.model.ApiResponse
import com.jppq.catalog.app.api.model.EmptyApiResponse
import com.jppq.catalog.app.api.model.ErrorApiResponse
import com.jppq.catalog.app.api.model.SuccessApiResponse
import com.jppq.catalog.app.viewmodel.EmptyResponse
import com.jppq.catalog.app.viewmodel.ErrorResponse
import com.jppq.catalog.app.viewmodel.SuccessResponse
import com.jppq.catalog.app.viewmodel.ViewModelResponse

fun <DO, DC> ApiResponse<DO>.parse(
    convert: ((data: DO) -> DC)
): ViewModelResponse<DC> {
    return when (this) {
        is SuccessApiResponse -> {
            SuccessResponse(
                convert.invoke(this.body)
            )
        }
        is EmptyApiResponse -> {
            EmptyResponse()
        }
        is ErrorApiResponse -> {
            ErrorResponse(
                this.errorCode,
                this.errorMessage
            )
        }
    }
}