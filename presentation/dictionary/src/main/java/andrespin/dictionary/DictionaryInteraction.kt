package andrespin.dictionary

import andrespin.domain.entity.PreviousWord
import andrespin.domain.entity.Word
import andrespin.presentation.Intent
import andrespin.presentation.State

sealed class DictionaryState : State {

    data object Loading : DictionaryState()

    data object UnknownException : DictionaryState()

    data object WordNetworkException : DictionaryState()

    data object NoKeyException : DictionaryState()

    data object InvalidKeyException : DictionaryState()

    data object NotFoundException : DictionaryState()

    data object NoConnectionException : DictionaryState()

    data class ShowPreviousWords(val words: List<PreviousWord>) : DictionaryState()

    data class ShowWord(val word: Word) : DictionaryState()

}

sealed class DictionaryIntent : Intent {

    object HidePreviousWords : DictionaryIntent()

    data class ShowPreviousWords(val query: String?) : DictionaryIntent()

    data class SearchNewWord(val word: String) : DictionaryIntent()

    data class GetPrevWord(val word: String) : DictionaryIntent()

}