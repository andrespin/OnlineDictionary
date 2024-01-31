package andrespin.domain.usecase.local.word

import andrespin.domain.entity.Word
import andrespin.domain.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InsertNewWordUseCase(private val wordRepo: WordRepository) {

    operator fun invoke(word: Word): Flow<Boolean> = flow {
        wordRepo.insertWord(word)
        emit(true)
    }.flowOn(Dispatchers.IO)

}