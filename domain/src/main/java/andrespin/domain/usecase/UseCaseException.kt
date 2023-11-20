package andrespin.domain.usecase

sealed class UseCaseException(cause: Throwable) : Throwable(cause) {

    class UnknownException(cause: Throwable) : UseCaseException(cause)

    class WordNetworkException(cause: Throwable) : UseCaseException(cause)

    class NoKeyException(cause: Throwable) : UseCaseException(cause)

    class InvalidKeyException(cause: Throwable) : UseCaseException(cause)

    class NotFoundException(cause: Throwable) : UseCaseException(cause)

    class NoConnectionException(cause: Throwable) : UseCaseException(cause)

    companion object {
        fun createFromThrowable(throwable: Throwable): UseCaseException {
            return if (throwable is UseCaseException) throwable else UnknownException(throwable)
        }
    }

    /*
        inner class NoKeyException : CustomCaughtExceptions()

    inner class InvalidKeyException : CustomCaughtExceptions()

    inner class NotFoundException : CustomCaughtExceptions()

    inner class NoConnectionException : CustomCaughtExceptions()
     */


}