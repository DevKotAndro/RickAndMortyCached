package com.shoshin.domain.common

abstract class Mapper<E, T> {
    abstract fun map(from: E): T

    fun map(fromList: List<E>): List<T> {
        return fromList.map { map(it) }
    }
}