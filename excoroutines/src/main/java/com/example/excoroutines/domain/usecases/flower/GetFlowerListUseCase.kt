package com.example.excoroutines.domain.usecases.flower

import com.example.excoroutines.domain.models.flower.FlowerModel
import com.example.excoroutines.domain.repositories.FlowerRepository
import com.example.shared.domain.ResponseResult
import com.example.shared.domain.executer.BaseJobUseCase
import com.example.shared.domain.executer.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

class GetFlowerListUseCase(
    private val coroutineContext: DispatcherProvider,
    private val flowerRepository: FlowerRepository
) : BaseJobUseCase<List<FlowerModel>>(coroutineContext) {

    override suspend fun getDataChannel(data: Map<String, Any>?): ReceiveChannel<ResponseResult<List<FlowerModel>>> {
        return flowerRepository.getFlowerList()
    }

    override suspend fun sendToPresentation(data: ResponseResult<List<FlowerModel>>): ResponseResult<List<FlowerModel>> {
        return data
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @FlowPreview
    fun getFlower(): Flow<ResponseResult<List<FlowerModel>>> {
        return produce().consumeAsFlow()
    }
}