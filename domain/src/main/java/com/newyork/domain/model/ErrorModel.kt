package com.newyork.domain.model

import java.lang.Exception

sealed class ErrorModel: Exception() {

    object Unknown: ErrorModel()
    object Connection: ErrorModel()
    class Network(val code: Int, val serverMessage: String?): ErrorModel()
}

