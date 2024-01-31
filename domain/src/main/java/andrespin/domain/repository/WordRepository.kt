package andrespin.domain.repository

import andrespin.domain.entity.Result
import andrespin.domain.entity.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getWords(): Flow<List<Word>>

    fun getWord(word: String): Flow<Word>

    fun getWord(word: String, lang: String, key: String): Flow<Result<Word>>

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)

}