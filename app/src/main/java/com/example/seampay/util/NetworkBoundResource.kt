package com.example.seampay.util

import android.util.Log
import com.example.seampay.model.Exhibit
import kotlinx.coroutines.flow.*
import java.lang.Exception

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (ResultType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(ExhibitMapper.mapToEntity(fetch() as List<Exhibit>) as ResultType)
            query().map { Resource.Success(it) }
        } catch (throwable: Exception) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}