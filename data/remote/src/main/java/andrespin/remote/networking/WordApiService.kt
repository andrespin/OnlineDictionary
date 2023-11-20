package andrespin.remote.networking

import retrofit2.http.GET
import retrofit2.http.Query

interface WordApiService {
    @GET("dicservice.json/lookup?")
    suspend fun getWordTranslation(
        @Query("key") key: String,
        @Query("lang") lang: String,
        @Query("text") text: String,
    ) : WordApiModel
}