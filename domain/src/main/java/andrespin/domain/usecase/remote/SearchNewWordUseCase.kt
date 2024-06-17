package andrespin.domain.usecase.remote

import andrespin.domain.entity.Word
import andrespin.domain.repository.WordRepository
import andrespin.domain.usecase.local.key.GetKeyUseCase
import andrespin.domain.usecase.sorter.GetWordLangUseCase
import andrespin.domain.entity.Result
import andrespin.domain.usecase.UseCaseException
import andrespin.domain.usecase.local.word.GetAllWordsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


class SearchNewWordUseCase(
    private val wordRepo: WordRepository,
    private val getWordLang: GetWordLangUseCase,
    private val getKeyUseCase: GetKeyUseCase,
    private val getAllWordsUseCase: GetAllWordsUseCase,
) {
    operator fun invoke(word: String): Flow<Result<Word>> = flow {
        val w = wordRepo.getWord(word).first()
        if (w != null) emit(Result.Success(w)) else try {
            emit(getWord(word))
        } catch (e: UseCaseException.NoKeyException) {
            emit(Result.Error(e))
        }
    }

    private suspend fun getWord(word: String): Result<Word> =
        when (val k = getKeyUseCase.invoke().first()) {
            is Result.Success -> {
                getWord(word, k.data)
            }

            is Result.Error -> {
                throw UseCaseException.NoKeyException(k.exception)
            }
        }

    private suspend fun getWord(word: String, key: String) =
        wordRepo.getWord(word, getWordLang.invoke(word).first().lang, key).first()

}



