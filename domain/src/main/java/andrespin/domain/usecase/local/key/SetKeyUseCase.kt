package andrespin.domain.usecase.local.key

import andrespin.domain.repository.KeyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SetKeyUseCase(private val keyRepo: KeyRepository) {

    operator fun invoke(key: String): Flow<Boolean> = flow {
        keyRepo.setKey(key)
        emit(true)
    }.flowOn(Dispatchers.IO).catch {

    }

}