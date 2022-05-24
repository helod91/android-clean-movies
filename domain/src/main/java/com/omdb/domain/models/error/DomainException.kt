package com.omdb.domain.models.error

import retrofit2.HttpException

data class DomainException(val code: String? = null, override val message: String? = null) : Throwable() {

    companion object {
        fun parseThrowable(throwable: Throwable): DomainException {
            val message = throwable.message
            return when (throwable) {
                is DomainException -> throwable
                is HttpException -> {
                    val code = ApiResponseCodeMapper.getDomainErrorCode(throwable.code())
                    DomainException(code, message)
                }
                else -> DomainException(DomainErrorCode.UNHANDLED, message)
            }
        }
    }
}