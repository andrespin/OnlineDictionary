package andrespin.local.source

import andrespin.data.local.LocalWordDataSource
import andrespin.domain.entity.Word
import andrespin.local.db.word.WordDao
import andrespin.local.mapToWordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalWordDataSourceImpl @Inject constructor(private val wordDao: WordDao) :
    LocalWordDataSource {

    override fun getWords(): Flow<List<Word>> = flow {
        emit(wordDao.getAllWords().map { word ->
            word.mapToWord()
        })
    }

    override fun getWord(word: String): Flow<Word?> = flow {
        println("wordDao.getWord(word).mapToWord() ${wordDao.getWord(word)?.mapToWord()}")
        emit(wordDao.getWord(word)?.mapToWord())
    }

    override suspend fun insertWord(word: Word) = wordDao.insertWord(word.mapToWordEntity())

    override suspend fun deleteWord(word: Word) = wordDao.deleteWord(word.mapToWordEntity())

}