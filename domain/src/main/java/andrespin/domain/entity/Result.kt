package andrespin.domain.entity

import andrespin.domain.usecase.UseCaseException

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    class Error(val exception: Throwable) : Result<Nothing>()
}