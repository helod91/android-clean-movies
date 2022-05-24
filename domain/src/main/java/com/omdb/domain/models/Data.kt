package com.omdb.domain.models

import com.omdb.domain.models.error.DomainException

data class Data<ResultData>(
    val responseType: Status,
    val data: ResultData? = null,
    val domainException: DomainException? = null
) {
    companion object {
        fun <ResultData> loading(): Data<ResultData> {
            return Data(Status.LOADING)
        }

        fun <ResultData> error(domainException: DomainException): Data<ResultData> {
            return Data(
                responseType = Status.ERROR,
                domainException = domainException
            )
        }

        fun <ResultData> success(data: ResultData): Data<ResultData> {
            return Data(
                responseType = Status.SUCCESSFUL,
                data = data
            )
        }
    }
}

enum class Status { SUCCESSFUL, ERROR, LOADING }