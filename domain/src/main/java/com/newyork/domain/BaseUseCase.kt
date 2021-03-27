package com.newyork.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class BaseUseCase {

    suspend fun <T> execute(job: suspend () -> T): T = withContext(Dispatchers.IO){
        try {
            return@withContext job()
        }
        catch (e: Exception) {
            Timber.e(e)
            throw e
        }
    }
}
