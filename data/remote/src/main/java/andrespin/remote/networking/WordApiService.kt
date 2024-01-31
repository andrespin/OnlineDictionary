package andrespin.remote.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WordApiService {
    @GET("dicservice.json/lookup?")
    suspend fun getWordTranslation(
        @Query("key") key: String,
        @Query("lang") lang: String,
        @Query("text") text: String,
    ) : Response<WordApiModel>


}
// dicservice.json/lookup?

// https://dictionary.yandex.net/api/v1/dicservice.json/lookup?

// https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=API-ключ&lang=en-ru&text=time



//  "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?"

// https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=API-ключ&lang=en-ru&text=time
// "dict.1.1.20240106T215927Z.3047be9fc89e7563.bbe6fdeccdf9fb3c49abcd0425acb617b9dc5c3b"

// https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20240106T215927Z.3047be9fc89e7563.bbe6fdeccdf9fb3c49abcd0425acb617b9dc5c3b&lang=en-ru&text=time