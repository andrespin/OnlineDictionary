package andrespin.domain.usecase.local.key

import andrespin.domain.repository.KeyRepository
import andrespin.domain.usecase.UseCaseException.NoKeyException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import andrespin.domain.entity.Result
import kotlinx.coroutines.flow.first

class GetKeyUseCase(private val keyRepo: KeyRepository) {
    operator fun invoke(): Flow<Result<String>> = flow {
        try {
            val k = keyRepo.getKey().first()
            emit(Result.Success(k))
        } catch (noKeyException: NoKeyException) {
            emit(Result.Error(noKeyException))
        }
    }
}