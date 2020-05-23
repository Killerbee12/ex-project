package com.example.excoroutines.data.api.flower

import com.example.excoroutines.data.model.flower.GetFlowerListResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FlowerApi {
    @GET("/flower_list.json")
    fun getFlowerListAsync(): Deferred<GetFlowerListResponseModel>
}