package com.example.shared.domain.executer

import com.example.shared.domain.ResponseResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

abstract class BaseJobUseCase<T>(private val coroutineContext: DispatcherProvider) {

    abstract suspend fun getDataChannel(data: Map<String, Any>? = null): ReceiveChannel<ResponseResult<T>>

    abstract suspend fun sendToPresentation(data: ResponseResult<T>): ResponseResult<T>

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    fun produce(withData: Map<String, Any>? = null): ReceiveChannel<ResponseResult<T>> {
        return GlobalScope.produce(context = coroutineContext.default()) {
            val dataChannel = getDataChannel(withData)
            dataChannel.consumeEach {
                send(sendToPresentation(it))
            }
        }
    }
}