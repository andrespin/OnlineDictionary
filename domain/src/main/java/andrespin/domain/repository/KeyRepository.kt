package andrespin.domain.repository

import kotlinx.coroutines.flow.Flow

interface KeyRepository {

    fun getKey(): Flow<String>

    suspend fun setKey(key: String)

}