package com.shoshin.data.remote

import com.shoshin.domain.common.Reaction

abstract class BaseRemoteSource {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T) : Reaction<T> {
        return try {
            Reaction.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Reaction.Error(throwable.message)
        }
    }
}