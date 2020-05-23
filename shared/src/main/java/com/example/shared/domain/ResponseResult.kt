package com.example.shared.domain

sealed class ResponseResult<T>

class SUCCESS<T>(var data: T? = null) : ResponseResult<T>()
class ERROR<T>(var error: Throwable = Throwable(), var data: T? = null) : ResponseResult<T>()