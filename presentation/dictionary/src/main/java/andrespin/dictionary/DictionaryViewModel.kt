package andrespin.dictionary

import andrespin.domain.entity.Result

import andrespin.domain.entity.PreviousWord

import andrespin.domain.entity.Word
import andrespin.domain.usecase.UseCaseException
import andrespin.domain.usecase.local.word.GetAllWordsUseCase
import andrespin.domain.usecase.remote.SearchNewWordUseCase
import andrespin.domain.usecase.sorter.SortPrevWordsUseCase
import andrespin.presentation.AppViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel
@Inject constructor(
    private val searchNewWordUseCase: SearchNewWordUseCase,
    private val getPrevWords: GetAllWordsUseCase,
    private val sortPrevWords: SortPrevWordsUseCase,
) : AppViewModel<DictionaryIntent, DictionaryState>() {

    init {
        handleIntent()
    }

    override fun handleIntent(): Job = viewModelScope.launch {
        getIntent.collectLatest {
            when (it) {
                is DictionaryIntent.SearchNewWord -> searchNewWord(it)
                is DictionaryIntent.ShowPreviousWords -> showPreviousWords(it)
                is DictionaryIntent.GetPrevWord -> getPrevWord(it)
                DictionaryIntent.HidePreviousWords -> hidePreviousWords()
            }
        }
    }

    override val vmTag: String
        get() = "DictionaryViewModel"

    private suspend fun searchNewWord(it: DictionaryIntent.SearchNewWord) {

        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e(vmTag, "${exception.printStackTrace()}")
        }

        viewModelScope.launch(handler) {
            emitState.emit(DictionaryState.Loading)
            Log.d(vmTag, "it.word ${it}")
            searchNewWordUseCase.invoke(it.word).collect{
                when(it) {
                    is Result.Success -> showWord(it)
                    is Result.Error -> showError(it.exception)
                }

            }

            Log.d(vmTag, "searchNewWord")

        }

    }

    private suspend fun showWord(it: Result.Success<Word>) {
        emitState.emit(DictionaryState.ShowWord(it.data))
    }

    private suspend fun showError(e: Throwable) {
        when (e) {
            is UseCaseException.WordNetworkException ->
                emitState.emit(DictionaryState.WordNetworkException)

            is UseCaseException.NoKeyException ->
                emitState.emit(DictionaryState.NoKeyException)

            is UseCaseException.InvalidKeyException ->
                emitState.emit(DictionaryState.InvalidKeyException)

            is UseCaseException.NotFoundException ->
                emitState.emit(DictionaryState.NotFoundException)

            is UseCaseException.NoConnectionException ->
                emitState.emit(DictionaryState.NoConnectionException)

            else ->
                emitState.emit(DictionaryState.UnknownException)
        }
        Log.d(vmTag, "it.exception ${e}")
    }

    private suspend fun showPreviousWords(it: DictionaryIntent.ShowPreviousWords) =
        getPrevWords.invoke().collect { words ->
            Log.d(vmTag, "prevWords $words")
            if (it.query.isNullOrEmpty()) {
                val convertedToPrevWords = convertToPrevWord(words)
                emitState.emit(DictionaryState.ShowPreviousWords(convertedToPrevWords))
            } else {
                val wordsHighlightedLetters = sortPrevWords.invoke(it.query, words).first()
                emitState.emit(DictionaryState.ShowPreviousWords(wordsHighlightedLetters))
            }
        }

    private fun convertToPrevWord(words: List<Word>): MutableList<PreviousWord> {
        val l = mutableListOf<PreviousWord>()
        for (i in words.indices) {
            l.add(PreviousWord(words[i], 0, 0))
        }
        return l
    }

//    private suspend fun showPreviousWords(it: DictionaryIntent.ShowPreviousWords) =
//        viewModelScope.launch {
//            Log.d(vmTag, "showPreviousWords")
//            val words = getPrevWords.invoke()
//            if (it.query == "") {
//                val convertedToPrevWords = convertToPrevWord(words)
//                emitState.emit(DictionaryState.ShowPreviousWords(convertedToPrevWords))
//            } else {
//                val wordsHighlightedLetters = sortPrevWords.invoke(it.query, words)
//                emitState.emit(DictionaryState.ShowPreviousWords(wordsHighlightedLetters))
//            }
//        }
//
//    private fun hidePreviousWords() = viewModelScope.launch {
//        val emptyPrevWordsList = mutableListOf<PreviousWord>()
//        emitState.emit(DictionaryState.ShowPreviousWords(emptyPrevWordsList))
//    }


    private suspend fun getPrevWord(it: DictionaryIntent.GetPrevWord) {

    }

    private fun hidePreviousWords() = viewModelScope.launch {
        val emptyPrevWordsList = mutableListOf<PreviousWord>()
        emitState.emit(DictionaryState.ShowPreviousWords(emptyPrevWordsList))
    }


}


