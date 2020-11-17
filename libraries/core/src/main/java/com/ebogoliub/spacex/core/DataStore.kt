package com.ebogoliub.spacex.core

import kotlinx.coroutines.flow.*

sealed class FetcherResult<T : Any> {
    data class Data<T : Any>(val value: T) : FetcherResult<T>()
    data class Error<T : Any>(val error: Throwable) : FetcherResult<T>()
}

data class FetcherParams<Params>(
    val params: Params?,
    val refresh: Boolean = false
)

interface Fetcher<Params : Any, Output : Any> {
    suspend operator fun invoke(params: Params?): Output

    companion object {
        fun <Params : Any, Output : Any> of(
            fetch: suspend (params: Params?) -> Output
        ): Fetcher<Params, Output> = object : Fetcher<Params, Output> {
            override suspend fun invoke(params: Params?): Output = fetch(params)
        }
    }
}

interface SourceOfTruth<Params : Any, Output : Any> {
    suspend fun reader(params: Params? = null): Flow<Output?>
    suspend fun writer(output: Output, params: Params? = null)
    suspend fun cacheValidator(output: Output, params: Params?): Boolean

    companion object {
        fun <Params : Any, Output : Any> of(
            reader: suspend (params: Params?) -> Flow<Output?>,
            writer: suspend (output: Output, params: Params?) -> Unit,
            cacheValidator: suspend (output: Output, params: Params?) -> Boolean
        ): SourceOfTruth<Params, Output> = object : SourceOfTruth<Params, Output> {
            override suspend fun reader(params: Params?): Flow<Output?> = reader(params)

            override suspend fun writer(output: Output, params: Params?) =
                writer(output, params)

            override suspend fun cacheValidator(output: Output, params: Params?): Boolean =
                cacheValidator(output, params)

        }
    }
}

interface DataStoreFactory {

    companion object {
        fun <Params : Any, Output : Any> from(
            fetcher: Fetcher<Params, Output>,
            sourceOfTruth: SourceOfTruth<Params, Output>
        ): DataStoreFactoryImpl<Params, Output> = DataStoreFactoryImpl(
            fetcher = fetcher,
            sourceOfTruth = sourceOfTruth
        )
    }
}

class DataStoreFactoryImpl<Params : Any, Output : Any>(
    private val fetcher: Fetcher<Params, Output>,
    private val sourceOfTruth: SourceOfTruth<Params, Output>,
) {

    suspend fun stream(
        params: FetcherParams<Params> = FetcherParams(
            null,
            false
        )
    ): Flow<FetcherResult<Output>> {
        return if (params.refresh) {
            flow { emit(fetcher(params.params)) }
                .onEach { sourceOfTruth.writer(it, params.params) }
                .flatMapConcat { sourceOfTruth.reader(params.params) }
        } else {
            sourceOfTruth.reader(params.params)
        }
            .onEach { data ->
                if (!params.refresh) {
                    if (data == null || !sourceOfTruth.cacheValidator(data, params.params)) {
                        val result = fetcher.invoke(params.params)
                        sourceOfTruth.writer(result, params.params)
                    }
                }
            }
            .filterNotNull()
            .map { FetcherResult.Data(it) as FetcherResult<Output> }
            .catch { error -> emit(FetcherResult.Error(error)) }
    }
}
