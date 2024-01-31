package andrespin.domain.usecase.sorter

import andrespin.domain.entity.Language
import andrespin.domain.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SortWordLangUseCase(private val getWordLang: GetWordLangUseCase) {

    operator fun invoke(words: List<Word>, lang: Language): Flow<List<Word>> = flow{
        emit(getSortedWords(words, lang))
    }.flowOn(Dispatchers.Default)

    private suspend fun getSortedWords(words: List<Word>, lang: Language): List<Word> {
        val l = mutableListOf<Word>()
        for (i in words.indices) {
            val language = getWordLang.invoke(words[i].txtOrig).first()
            if (language == lang) {
                l.add(words[i])
            }
        }
        return l
    }

}
