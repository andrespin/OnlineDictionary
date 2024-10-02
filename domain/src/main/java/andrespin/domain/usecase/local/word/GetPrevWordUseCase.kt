package andrespin.domain.usecase.local.word

import andrespin.domain.entity.Word
import andrespin.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class GetPrevWordUseCase(private val wordRepo: WordRepository) {
    operator fun invoke(word: String): Flow<Word?> = wordRepo.getWord(word)
}