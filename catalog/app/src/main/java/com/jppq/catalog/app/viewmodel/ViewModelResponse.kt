package com.jppq.catalog.app.viewmodel

import com.jppq.catalog.app.utils.ErrorConstants.Companion.GENERIC
import com.jppq.catalog.app.utils.LoggerConstants.Companion.MESSAGE_NOT_AVAILABLE

sealed class ViewModelResponse<T>

class EmptyResponse<T> : ViewModelResponse<T>()

data class SuccessResponse<T>(
    val body: T
) : ViewModelResponse<T>()

data class ErrorResponse<T>(
    val errorCode: Int = GENERIC,
    val errorMessage: String? = MESSAGE_NOT_AVAILABLE
) : ViewModelResponse<T>()