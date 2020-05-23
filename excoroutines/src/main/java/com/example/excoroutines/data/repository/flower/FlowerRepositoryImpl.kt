package com.example.excoroutines.data.repository.flower

import com.example.excoroutines.domain.models.flower.FlowerModel
import com.example.excoroutines.domain.repositories.FlowerRepository
import com.example.shared.domain.ResponseResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class FlowerRepositoryImpl(private val remoteFlowerDataSource: RemoteFlowerDataSource) :
    FlowerRepository {
    override suspend fun getFlowerList(): ReceiveChannel<ResponseResult<List<FlowerModel>>> {
        return GlobalScope.produce {
            send(remoteFlowerDataSource.getFlower().receive())
        }
    }
}