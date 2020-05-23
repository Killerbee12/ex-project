package com.example.shared.network

import com.example.shared.network.AdapterFactory.Companion.RESPONSE_BODY_NULL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class AdapterFactory : CallAdapter.Factory() {
    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke() = CoroutineCallAdapterFactory()

        const val RESPONSE_BODY_NULL = "RESPONSE_BODY_IS_NULL"
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Deferred::class.java != getRawType(returnType)) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "Deferred return type must be parameterized as Deferred<Foo> or Deferred<out Foo>"
            )
        }
        val responseType = getParameterUpperBound(0, returnType)

        val rawDeferredType = getRawType(responseType)
        return if (rawDeferredType == Response::class.java) {
            if (responseType !is ParameterizedType) {
                throw IllegalStateException(
                    "Response must be parameterized as Response<Foo> or Response<out Foo>"
                )
            }
            ResponseCallAdapter<Any>(
                getParameterUpperBound(
                    0,
                    responseType
                )
            )
        } else {
            BodyCallAdapter<Any>(responseType)
        }
    }
}

private class BodyCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Deferred<T>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Deferred<T> {
        val deferred = CompletableDeferred<T>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                deferred.completeExceptionally(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        deferred.complete(it)
                    } ?: deferred.completeExceptionally(Exception(RESPONSE_BODY_NULL))
                } else {
                    deferred.completeExceptionally(HttpException(response))
                }
            }
        })

        return deferred
    }
}

private class ResponseCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Deferred<Response<T>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Deferred<Response<T>> {
        val deferred = CompletableDeferred<Response<T>>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                deferred.completeExceptionally(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                deferred.complete(response)
            }
        })

        return deferred
    }
}
