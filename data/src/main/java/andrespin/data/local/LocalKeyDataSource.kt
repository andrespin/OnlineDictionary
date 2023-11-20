package andrespin.data.local

import kotlinx.coroutines.flow.Flow

interface LocalKeyDataSource {

    suspend fun setKey(key: String)

    fun getKey(): Flow<String>

}