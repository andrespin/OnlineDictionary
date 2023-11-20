package andrespin.remote.source

import andrespin.data.remote.RemoteWordDataSource
import andrespin.domain.entity.Word
import andrespin.domain.usecase.UseCaseException
import andrespin.remote.networking.WordApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RemoteWordDataSourceImpl
@Inject constructor(private val wordApiService: WordApiService) : RemoteWordDataSource {
    override fun getWord(word: String, lang: String, key: String): Flow<Word> = flow {
        emit(wordApiService.getWordTranslation(word, lang, key))
    }.map { wordApiModel ->
        wordApiModel.mapToWord()
    }.catch {
        throw UseCaseException.WordNetworkException(it)
    }
}

