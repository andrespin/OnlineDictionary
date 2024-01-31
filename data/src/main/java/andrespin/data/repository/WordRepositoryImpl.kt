package andrespin.data.repository

import andrespin.data.local.LocalKeyDataSource
import andrespin.data.local.LocalWordDataSource
import andrespin.data.remote.RemoteWordDataSource
import andrespin.domain.entity.Result
import andrespin.domain.entity.Word
import andrespin.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class WordRepositoryImpl(
    private val local: LocalWordDataSource,
    private val remote: RemoteWordDataSource,
) : WordRepository {
    override fun getWords(): Flow<List<Word>> = local.getWords()

    override fun getWord(word: String): Flow<Word> = local.getWord(word)
    override fun getWord(word: String, lang: String, key: String): Flow<Result<Word>> =
        remote.getWord(word, lang, key)
            .onEach {
                if (it is Result.Success)
                    local.insertWord(it.data)
            }

    override suspend fun insertWord(word: Word) = local.insertWord(word)
    override suspend fun deleteWord(word: Word) = local.deleteWord(word)

}