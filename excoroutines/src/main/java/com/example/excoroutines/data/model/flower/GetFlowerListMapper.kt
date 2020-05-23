package com.example.excoroutines.data.model.flower

import com.example.excoroutines.domain.models.flower.FlowerModel

class GetFlowerListMapper {
    fun mapFrom(from: GetFlowerListResponseModel): List<FlowerModel> {
        return from.data?.map {
            FlowerModel(
                id = it.id,
                name = it.name
            )
        } ?: listOf()
    }
}