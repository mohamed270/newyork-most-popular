package com.newyork.data

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.newyork.domain.model.ErrorModel
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> execute(job: suspend () -> T): T {
        try {
            return job()
        }
        catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
            Timber.e(e)
            when(e) {

                is IOException -> throw ErrorModel.Connection
                is HttpException -> throw(e)
            }
            throw e
        }
    }

}