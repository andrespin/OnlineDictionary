package andrespin.remote.source

import andrespin.data.remote.RemoteWordDataSource
import andrespin.domain.entity.Result
import andrespin.domain.entity.Word
import andrespin.domain.usecase.UseCaseException
import andrespin.remote.networking.WordApiService
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RemoteWordDataSourceImpl
@Inject constructor(private val wordApiService: WordApiService) : RemoteWordDataSource {

    private val tag = "RemoteWordDataSourceImpl"

    override fun getWord(word: String, lang: String, key: String): Flow<Result<Word>> =
        flow<Result<Word>> {
           // throw java.net.SocketTimeoutException()
            Log.d(tag, "word $word , lang $lang, key $key ")
            val res = wordApiService.getWordTranslation(key, lang, word)

            if (res.isSuccessful) {
                emit(Result.Success(res.body()!!.mapToWord()))
            } else {
                Log.d(tag, "res.code ${res.code()} ${res.body()}")
                Log.d(tag, "res.message() ${res.message()} res.errorBody() ${res.errorBody()}")
//                res.message()
//                res.errorBody()
                // code 401
                when (res.code()) {
                    401 -> {
                        Log.d(tag, "res.code is ${res.code()} ${res.body()}")
                        throw UseCaseException.InvalidKeyException(
                            Exception(
                                res.errorBody().toString()
                            )
                        )
                    }

                    403 -> {
                        Log.d(tag, "res.code is ${res.code()} ${res.body()}")
                        throw UseCaseException.InvalidKeyException(
                            Exception(
                                res.errorBody().toString()
                            )
                        )
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                Log.d("RemoteWordDataSourceImpl", " ${it}")
                when (it) {
                    is java.net.UnknownHostException -> emit(Result.Error(UseCaseException.NoConnectionException(it)))
                    is java.lang.IndexOutOfBoundsException -> emit(Result.Error(UseCaseException.NotFoundException(it)))
                    is java.net.SocketTimeoutException -> emit(Result.Error(UseCaseException.WordNetworkException(it)))
                    else -> emit(Result.Error(it))
                }

            }

    /*
    java.net.SocketTimeoutException: failed to connect to dictionary.yandex.net/2a02:6b8::196 (port 443) from /fec0::a536:87ed:feeb:a350 (port 39340) after 10000ms

java.net.UnknownHostException: Unable to resolve host "dictionary.yandex.net": No address associated with hostname

java.lang.IndexOutOfBoundsException


    andrespin.domain.usecase.UseCaseException$InvalidKeyException: java.lang.Exception: okhttp3.ResponseBody$Companion$asResponseBody$1@65ce49e
     */


//    override fun getWord(word: String, lang: String, key: String): Flow<Result<Word>> = flow {
//
//        Log.d("RemoteWordDataSourceImpl", "word $word , lang $lang, key $key ")
//        val res = wordApiService.getWordTranslation(key, lang, word)
//
//        if (res.isSuccessful) {
//            emit(res.body())
//        } else {
//            Log.d("RemoteWordDataSourceImpl", "res.code ${res.code()} ${res.body()}")
//            when (res.code()) {
//                401 -> {
//                    Log.d("RemoteWordDataSourceImpl", "res.code is ${res.code()} ${res.body()}")
//                    throw UseCaseException.InvalidKeyException(
//                        Exception(
//                            res.errorBody().toString()
//                        )
//                    )
//                }
//
//                403 -> {
//                    Log.d("RemoteWordDataSourceImpl", "res.code is ${res.code()} ${res.body()}")
//                    throw UseCaseException.InvalidKeyException(
//                        Exception(
//                            res.errorBody().toString()
//                        )
//                    )
//                }
//            }
//        }
//    }.flowOn(Dispatchers.IO)
//        .map { wordApiModel ->
//            Result.Success(wordApiModel!!.mapToWord())
//        }
//        .catch {
//            emit(Result.Error(Exception("")))
//
////            Log.d("RemoteWordDataSourceImpl", "exceptions is caught ${it}}")
////            when (it) {
////                is UseCaseException.InvalidKeyException -> {
////                    throw UseCaseException.InvalidKeyException(
////                        Exception(
////                            it.message
////                        )
////                    )
////                }
////
////                else -> {
////                    throw UseCaseException.WordNetworkException(it)
////                }
////            }
//
//        }


}

