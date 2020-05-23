package com.example.excoroutines.presentation.flowerlist

import androidx.lifecycle.MutableLiveData
import com.example.excoroutines.domain.models.flower.FlowerModel
import com.example.excoroutines.domain.usecases.flower.GetFlowerListUseCase
import com.example.shared.common.BaseViewModel
import com.example.shared.common.Error
import com.example.shared.common.HideLoading
import com.example.shared.common.ShowLoading
import com.example.shared.domain.ERROR
import com.example.shared.domain.SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.*

class FlowerListViewModel(
    private val getFlowerListUseCase: GetFlowerListUseCase
) : BaseViewModel() {

    private val flowerList = MutableLiveData<List<FlowerModel>>()

    @FlowPreview
    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    fun getFlowerList() {
        getFlowerListUseCase.getFlower()
            .onStart { viewState.postValue(ShowLoading) }
            .catch { viewState.postValue(HideLoading) }
            .onCompletion { viewState.postValue(HideLoading) }
            .onEach {
                when (it) {
                    is SUCCESS -> {
                        flowerList.postValue(it.data)
                    }
                    is ERROR -> viewState.postValue(Error(it.error))
                }
            }.launchIn(this)
    }

    fun getFlowerListLiveData() = flowerList
}