package com.example.excoroutines.data.repository.flower

import com.example.excoroutines.domain.models.flower.FlowerModel
import com.example.shared.domain.ResponseResult
import kotlinx.coroutines.channels.ReceiveChannel

interface FlowerDataSource {
    fun getFlower(): ReceiveChannel<ResponseResult<List<FlowerModel>>>
}