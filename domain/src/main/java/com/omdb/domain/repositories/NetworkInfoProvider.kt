package com.omdb.domain.repositories

interface NetworkInfoProvider {
    fun hasInternetConnection(): Boolean
}