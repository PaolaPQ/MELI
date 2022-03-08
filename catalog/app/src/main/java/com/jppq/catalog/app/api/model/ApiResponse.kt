package com.jppq.catalog.app.api.model

/**
 * This is a new common class used by API responses.
 */
sealed class ApiResponse<out Data>

class EmptyApiResponse: ApiResponse<Nothing>()

data class SuccessApiResponse<out Data>(
    val body: Data
) : ApiResponse<Data>()

data class ErrorApiResponse(
    val errorCode: Int = -1,
    val errorMessage: String? = ""
) : ApiResponse<Nothing>()