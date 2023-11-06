package andrespin.domain.usecase.sorter

import andrespin.domain.entity.Language
import andrespin.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale

class GetWordLangUseCase(configuration: Configuration) :
    UseCase<GetWordLangUseCase.Request, GetWordLangUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> {
        val res = getLang(request.word)
        return flow{
            emit(Response(res))
        }
    }

    data class Request(val word: String) : UseCase.Request
    data class Response(val language: Language) : UseCase.Response


    private fun getLang(word: String): Language {

        val w = word.lowercase(Locale.ROOT)

        val array = w.toCharArray()

        val isEng = isEng(array)
        val isRus = isRus(array)

        return if (isEng) {
            Language.English
        } else if (isRus) {
            Language.Russian
        } else {
            Language.NotIdentified
        }

    }

    private fun isEng(array: CharArray): Boolean {
        var isTrue = false
        for (i in array.indices) {
            if (array[i] in 'a'..'z' || array[i] == ' ') {
                isTrue = true
            } else {
                return false
            }
        }
        return isTrue
    }

    private fun isRus(array: CharArray): Boolean {
        var isTrue = false
        for (i in array.indices) {
            if (array[i] in 'а'..'я' || array[i] == ' ') {
                isTrue = true
            } else {
                return false
            }
        }
        return isTrue
    }


}