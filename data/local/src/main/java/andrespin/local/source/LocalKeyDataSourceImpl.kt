package andrespin.local.source

import andrespin.data.local.LocalKeyDataSource
import andrespin.domain.NoKey
import andrespin.domain.usecase.UseCaseException
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
       return dataStore.data.map {
           it[API_KEY] ?: throw UseCaseException.NoKeyException(Exception(NoKey))
        }
    }

}
