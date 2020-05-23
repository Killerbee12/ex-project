package com.example.excoroutines.di

import com.example.excoroutines.data.api.flower.FlowerApi
import com.example.excoroutines.data.repository.flower.FlowerRepositoryImpl
import com.example.excoroutines.data.repository.flower.RemoteFlowerDataSource
import com.example.excoroutines.domain.repositories.FlowerRepository
import com.example.excoroutines.domain.usecases.flower.GetFlowerListUseCase
import com.example.excoroutines.presentation.flowerlist.FlowerListViewModel
import com.example.shared.domain.executer.DefaultDispatcherProvider
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val exCoroutinesModule = module {
    single { (get() as Retrofit).create(FlowerApi::class.java) }
    single { RemoteFlowerDataSource(api = get()) }
    single<FlowerRepository> { FlowerRepositoryImpl(remoteFlowerDataSource = get()) }

    factory {
        GetFlowerListUseCase(
            coroutineContext = DefaultDispatcherProvider(),
            flowerRepository = get()
        )
    }

    viewModel {
        FlowerListViewModel(getFlowerListUseCase = get())
    }
}