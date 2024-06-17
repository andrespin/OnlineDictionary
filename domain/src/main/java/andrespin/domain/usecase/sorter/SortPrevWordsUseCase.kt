package andrespin.domain.usecase.sorter

import andrespin.domain.entity.PreviousWord
import andrespin.domain.entity.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SortPrevWordsUseCase(
    private val matchingLetters: FindMatchingLettersInWordUseCase,
    private val langSorter: SortWordLangUseCase,
    private val getWordLang: GetWordLangUseCase,
) {
    operator fun invoke(query: String, words: List<Word>): Flow<List<PreviousWord>> = flow{
        val lang = getWordLang.invoke(query).first()
        val sortedWords = langSorter.invoke(words, lang).first()
        val wordsHighlightedLetters = matchingLetters.invoke(sortedWords, query).first()
        emit(wordsHighlightedLetters)
    }

}
