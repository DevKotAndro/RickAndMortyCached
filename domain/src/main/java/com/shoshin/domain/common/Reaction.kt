package com.shoshin.domain.common

sealed class Reaction<out T> {
    data class Progress<out T>(val data: T? = null) : Reaction<T>() {}
    data class Success<out T>(val data: T) : Reaction<T>()
    data class Error<out T>(
        val message: String? = null,
        val data: T? = null
    ): Reaction<T>()

    fun <B> map(func: (T) -> B): Reaction<B> {
        return when(this) {
            is Success -> Success(data = func(data))
            is Progress -> Progress(data = if(data == null) null else func(data))
            is Error -> Error(
                message = this.message,
                data = if(data == null) null else func(data)
            )
        }
    }
}
