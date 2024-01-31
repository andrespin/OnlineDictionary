package andrespin.domain.usecase.sorter

import andrespin.domain.entity.Language
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale


class GetWordLangUseCase {

    operator fun invoke(word: String): Flow<Language> = flow {
        val l = getLang(word)
        Log.d("GetWordLangUseCase", "lang of $word is $l")
//        throw Exception("GetWordLangUseCaseException")
        emit(l)
    }

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
