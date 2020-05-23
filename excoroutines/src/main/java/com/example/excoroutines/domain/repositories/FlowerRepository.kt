package com.example.excoroutines.domain.repositories

import com.example.excoroutines.domain.models.flower.FlowerModel
import com.example.shared.domain.ResponseResult
import kotlinx.coroutines.channels.ReceiveChannel

interface FlowerRepository {
    suspend fun getFlowerList(): ReceiveChannel<ResponseResult<List<FlowerModel>>>
}