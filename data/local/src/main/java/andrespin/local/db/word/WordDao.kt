package andrespin.local.db.word

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {

    @Insert
    suspend fun insertWord(wordEntity: WordEntity)

    @Insert
    suspend fun deleteWord(wordEntity: WordEntity)

    @Query("DELETE FROM words_table")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM words_table")
    suspend fun getAllWords(): List<WordEntity>

    @Query("SELECT * FROM words_table where txtOrig=:w")
    suspend fun getWord(w: String): WordEntity?

}