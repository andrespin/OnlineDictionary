package andrespin.domain.usecase.sorter

import andrespin.domain.entity.PreviousWord
import andrespin.domain.entity.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FindMatchingLettersInWordUseCase() {

    operator fun invoke(words: List<Word>, query: String) : Flow<List<PreviousWord>> {
        val mLetters = findMatchingLettersInWords(words, query)
        return flow {
            emit(mLetters)
        }
    }

    private fun findMatchingLettersInWords(
        words: List<Word>,
        query: String,
    ): List<PreviousWord> {
        val l = mutableListOf<PreviousWord>()
        for (i in words.indices) {
            val w = findMatchingLettersInWord(words[i], query)
            if (w.end != 0) {
                l.add(findMatchingLettersInWord(words[i], query))
            }
        }
        return l
    }


    private fun findMatchingLettersInWord(word: Word, query: String): PreviousWord {

        val q = query.lowercase()
        val w = word.txtOrig.lowercase()
        val arrayWord = w.toCharArray()
        val arrayQuery = q.toCharArray()

        var j = 0
        var start = 0
        var end = 0
        while (j < arrayQuery.size && j < arrayWord.size) {
            if (arrayWord[j] == arrayQuery[j]) {
                end += 1
            } else {
                end = 0
                break
            }
            j++
        }
        return PreviousWord(word, start, end)
    }

}


