package andrespin.data.remote

import andrespin.domain.entity.Word
import kotlinx.coroutines.flow.Flow

interface RemoteWordDataSource {

    fun getWord(word: String, lang: String, key: String): Flow<Word>

}