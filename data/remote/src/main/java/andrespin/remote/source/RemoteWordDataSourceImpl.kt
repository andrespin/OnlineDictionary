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
            val res = wordApiService.getWordTranslation(key, lang, word)
            if (res.isSuccessful) {
                emit(Result.Success(res.body()!!.mapToWord()))
            } else {
                when (res.code()) {
                    401 -> {
                        throw UseCaseException.InvalidKeyException(
                            Exception(
                                res.errorBody().toString()
                            )
                        )
                    }

                    403 -> {
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
                when (it) {
                    is java.net.UnknownHostException -> emit(Result.Error(UseCaseException.NoConnectionException(it)))
                    is java.lang.IndexOutOfBoundsException -> emit(Result.Error(UseCaseException.NotFoundException(it)))
                    is java.net.SocketTimeoutException -> emit(Result.Error(UseCaseException.WordNetworkException(it)))
                    else -> emit(Result.Error(it))
                }
            }

}

