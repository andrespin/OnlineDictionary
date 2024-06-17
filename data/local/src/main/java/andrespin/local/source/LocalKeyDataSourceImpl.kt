package andrespin.local.source

import andrespin.data.local.LocalKeyDataSource
import andrespin.domain.NoKey
import andrespin.domain.usecase.UseCaseException
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal val API_KEY = stringPreferencesKey("api_key")

class LocalKeyDataSourceImpl @Inject constructor (private val dataStore: DataStore<Preferences>) : LocalKeyDataSource {

    override suspend fun setKey(key: String) {
        dataStore.edit {
            it[API_KEY] = key
        }
    }

    override fun getKey(): Flow<String>  {
//        throw UseCaseException.NoKeyException(Exception(NoKey))
       return dataStore.data.map {
           it[API_KEY] ?: throw UseCaseException.NoKeyException(Exception(NoKey))
//           "dict.1.1.20240106T215927Z.3047be9fc89e7563.bbe6fdeccdf9fb3c49abcd0425acb617b9dc5c3b"
        }
    }
    //             it[API_KEY] ?: throw UseCaseException.NoKeyException(Exception(NoKey))

    // "dict.1.1.20240106T215927Z.3047be9fc89e7563.bbe6fdeccdf9fb3c49abcd0425acb617b9dc5c3b"


    // https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20240106T215927Z.3047be9fc89e7563.bbe6fdeccdf9fb3c49abcd0425acb617b9dc5c3b&lang=en-ru&text=time


   // https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20240106T215927Z.3047be9fc89e7563.bbe6fdeccdf9fb3c49abcd0425acb617b9dc5c3b&lang=en-ru&text=wordToTestThatDoesNotExhist




}
