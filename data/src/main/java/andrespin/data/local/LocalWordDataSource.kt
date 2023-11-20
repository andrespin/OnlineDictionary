package andrespin.data.local

import andrespin.domain.entity.Word
import kotlinx.coroutines.flow.Flow

interface LocalWordDataSource {

    fun getWords(): Flow<List<Word>>

    fun getWord(word: String): Flow<Word>

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)

}