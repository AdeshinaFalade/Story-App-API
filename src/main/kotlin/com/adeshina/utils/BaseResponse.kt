package com.adeshina.utils

import io.ktor.http.*

sealed class BaseResponse<T> (
    open val statusCode: HttpStatusCode = HttpStatusCode.OK
){
    data class SuccessResponse<T>(
        override val statusCode: HttpStatusCode = HttpStatusCode.OK,
        val data: T? = null,
        val message: String? = null
    ): BaseResponse<T>()

    data class ErrorResponse<T>(
        override val statusCode: HttpStatusCode = HttpStatusCode.BadRequest,
        val exception: T? = null,
        val message: String? = null
    ): BaseResponse<T>()
}