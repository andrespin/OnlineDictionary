package andrespin.domain.usecase.local.word

import andrespin.domain.entity.Word
import andrespin.domain.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllWordsUseCase(private val wordRepo: WordRepository) {

    operator fun invoke(): Flow<List<Word>> = flow {
        emit(wordRepo.getWords().first())
    }.flowOn(Dispatchers.IO)

}