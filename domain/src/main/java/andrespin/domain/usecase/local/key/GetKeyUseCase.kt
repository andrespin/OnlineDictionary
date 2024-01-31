package andrespin.domain.usecase.local.key

import andrespin.domain.repository.KeyRepository
import andrespin.domain.usecase.UseCaseException
import andrespin.domain.usecase.UseCaseException.NoKeyException
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetKeyUseCase(private val keyRepo: KeyRepository) {

    operator fun invoke(): Flow<String> =
        try {
//            val k = keyRepo.getKey()
//            Log.d("GetKeyUseCase", "key is ${k}")
//            k
//            keyRepo.getKey()
            throw NoKeyException(Exception("GetKeyUseCaseException"))
        } catch (noKeyException: NoKeyException) {
            throw NoKeyException(Exception(noKeyException.message))
        }

//            .catch {
//                when(it) {
//                    is NoKeyException -> throw NoKeyException(Exception(it.message))
//                    else -> throw UseCaseException.createFromThrowable(it)
//                }
//            }

//    operator fun invoke(): Flow<String> = flow {
//        emit(keyRepo.getKey().first())
//    }.catch {
//        when(it) {
//            is NoKeyException -> throw NoKeyException(Exception())
//            else -> throw UseCaseException.createFromThrowable(it)
//        }
//    }

}