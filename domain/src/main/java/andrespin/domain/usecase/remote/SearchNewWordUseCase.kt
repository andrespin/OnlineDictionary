package andrespin.domain.usecase.remote

import andrespin.domain.entity.Word
import andrespin.domain.repository.WordRepository
import andrespin.domain.usecase.local.key.GetKeyUseCase
import andrespin.domain.usecase.sorter.GetWordLangUseCase
import andrespin.domain.entity.Result
import andrespin.domain.usecase.UseCaseException
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SearchNewWordUseCase(
    private val wordRepo: WordRepository,
    private val getWordLang: GetWordLangUseCase,
    private val getKeyUseCase: GetKeyUseCase,
) {

    operator fun invoke(word: String): Flow<Result<Word>> = try {
        combine(
            getWordLang.invoke(word),
            getKeyUseCase.invoke()
        ) { lang, key ->
            Log.d("SearchNewWordUseCase", "lang $lang key $key")
            wordRepo.getWord(word, lang.lang, key).first()
        }
    } catch (e: UseCaseException.NoKeyException) {
        flow{
            emit(Result.Error(e))
        }
    }

//
//        combine(
//            getWordLang.invoke(word),
//            getKeyUseCase.invoke()
//        ) { lang, key ->
//            Log.d("SearchNewWordUseCase", "lang $lang key $key")
//            wordRepo.getWord(word, lang.lang, key).first()
//        }.catch {
//            Log.d("SearchNewWordUseCase", "Exception $it")
//        }
}



