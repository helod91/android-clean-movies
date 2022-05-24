package com.omdb.domain.usecases

import com.omdb.domain.models.Data
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
abstract class UseCase<Params, Result> {

    protected abstract suspend fun provideData(params: Params): Data<Result>

    fun execute(params: Params): Flow<Data<Result>> {
        return flow {
            emit(provideData(params))
        }.onStart {
            emit(Data.loading())
        }.catch { exception ->
            emit(Data.error(exception))
        }
    }
}