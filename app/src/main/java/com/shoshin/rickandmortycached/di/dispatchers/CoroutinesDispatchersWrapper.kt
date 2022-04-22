package com.shoshin.rickandmortycached.di.dispatchers

import com.shoshin.domain.common.dispatchers.DispatchersWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutinesDispatchersWrapper: DispatchersWrapper {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val default: CoroutineDispatcher = Dispatchers.Default
    override val io: CoroutineDispatcher = Dispatchers.IO
}