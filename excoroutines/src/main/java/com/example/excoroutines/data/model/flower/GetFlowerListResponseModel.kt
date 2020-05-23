package com.example.excoroutines.data.model.flower

import com.example.excoroutines.domain.models.flower.FlowerModel
import com.google.gson.annotations.SerializedName

data class GetFlowerListResponseModel(
    @SerializedName("data") val data: List<FlowerModel>? = null
)