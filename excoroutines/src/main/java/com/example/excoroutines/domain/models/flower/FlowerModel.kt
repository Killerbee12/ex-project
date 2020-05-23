package com.example.excoroutines.domain.models.flower

import kotlinx.serialization.Serializable

@Serializable()
data class FlowerModel(
    var id: String? = "",
    var name: String? = ""
)