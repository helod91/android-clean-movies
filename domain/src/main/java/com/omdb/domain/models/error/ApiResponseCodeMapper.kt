package com.omdb.domain.models.error

object ApiResponseCodeMapper {

    private val errorMatcher = HashMap<Int, String>().apply {
        put(ApiResponseCode.UNAUTHORIZED, DomainErrorCode.UNAUTHORIZED)
    }

    fun getDomainErrorCode(apiErrorCode: Int): String {
        return errorMatcher[apiErrorCode] ?: apiErrorCode.toString()
    }
}