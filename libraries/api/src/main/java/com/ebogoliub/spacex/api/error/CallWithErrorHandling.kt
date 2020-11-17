package com.ebogoliub.spacex.api.error

import retrofit2.*
import java.io.IOException

class CallWithErrorHandling(
    private val delegate: Call<Any>
) : Call<Any> by delegate {

    override fun enqueue(callback: Callback<Any>) {
        delegate.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onResponse(call, response)
                } else {
                    callback.onFailure(call, mapToDomainException(HttpException(response)))
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                callback.onFailure(call, mapToDomainException(t as Exception))
            }
        })
    }

    override fun clone() = CallWithErrorHandling(delegate.clone())

    fun mapToDomainException(
        remoteException: Exception,
        httpExceptionsMapper: (HttpException) -> Exception? = { null }
    ): Exception {
        return when (remoteException) {
            is IOException -> NoInternetException()
            is HttpException -> httpExceptionsMapper(remoteException) ?: ApiException(remoteException.code().toString())
            else -> UnexpectedException(remoteException)
        }
    }
}