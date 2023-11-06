package andrespin.domain.usecase.sorter

import andrespin.domain.entity.Language
import andrespin.domain.entity.PreviousWord
import andrespin.domain.entity.Word
import andrespin.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FindMatchingLettersInWordUseCase(configuration: Configuration) :
    UseCase<FindMatchingLettersInWordUseCase.Request, FindMatchingLettersInWordUseCase.Response>(
        configuration
    ) {
    override fun process(request: FindMatchingLettersInWordUseCase.Request)
            : Flow<FindMatchingLettersInWordUseCase.Response> {
        val mLetters = findMatchingLettersInWords(request.words, request.query)
        return flow{
            emit(Response(mLetters))
        }
    }

    data class Request(val words: List<Word>, val query: String) : UseCase.Request
    data class Response(val list: List<PreviousWord>) : UseCase.Response

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

