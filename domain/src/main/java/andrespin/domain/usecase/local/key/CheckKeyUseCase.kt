package andrespin.domain.usecase.local.key

import andrespin.domain.NoKey
import andrespin.domain.entity.Language
import andrespin.domain.entity.Result
import andrespin.domain.repository.KeyRepository
import andrespin.domain.repository.WordRepository
import andrespin.domain.usecase.UseCaseException
import andrespin.domain.usecase.remote.SearchNewWordUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckKeyUseCase(
    private val keyRepo: KeyRepository,
    private val wordRepo: WordRepository
) {

    private val testWord = "dog"

    operator fun invoke(key: String): Flow<Result<Boolean>> =
        flow {
            wordRepo.getWord(testWord, Language.English.lang, key).collect {
                when (it) {
                    is Result.Success -> emit(Result.Success(process(key)))
                    is Result.Error -> emit(Result.Error(it.exception))
                }
            }
        }

    private suspend fun process(key: String): Boolean =
        setKey(key)

    private suspend fun setKey(key: String): Boolean {
        keyRepo.setKey(key)
        return true
    }

}