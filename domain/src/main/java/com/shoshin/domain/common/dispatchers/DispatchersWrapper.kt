package com.shoshin.domain.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersWrapper {
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
}