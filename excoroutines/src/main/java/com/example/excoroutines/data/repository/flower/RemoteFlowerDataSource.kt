package com.example.excoroutines.data.repository.flower

import com.example.excoroutines.data.api.flower.FlowerApi
import com.example.excoroutines.data.model.flower.GetFlowerListMapper
import com.example.excoroutines.domain.models.flower.FlowerModel
import com.example.shared.domain.ERROR
import com.example.shared.domain.ResponseResult
import com.example.shared.domain.SUCCESS
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class RemoteFlowerDataSource(private val api: FlowerApi) : FlowerDataSource {
    private val mapper = GetFlowerListMapper()
    override fun getFlower(): ReceiveChannel<ResponseResult<List<FlowerModel>>> {
        return GlobalScope.produce {
            try {
                val response = api.getFlowerListAsync().await()
                send(SUCCESS(mapper.mapFrom(response)))
            } catch (e: Exception) {
                send(ERROR(error = Throwable(e.message)))
            }
        }
    }
}