package andrespin.data.repository

import andrespin.data.local.LocalKeyDataSource
import andrespin.domain.repository.KeyRepository
import kotlinx.coroutines.flow.Flow

class KeyRepositoryImpl(private val keyDataSource: LocalKeyDataSource) : KeyRepository {
    override fun getKey(): Flow<String> = keyDataSource.getKey()

    override suspend fun setKey(key: String) = keyDataSource.setKey(key)

}