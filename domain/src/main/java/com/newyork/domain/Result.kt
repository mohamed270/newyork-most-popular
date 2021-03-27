package com.newyork.domain

import com.newyork.domain.model.ErrorModel

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: ErrorModel) : Result<Nothing>()
}