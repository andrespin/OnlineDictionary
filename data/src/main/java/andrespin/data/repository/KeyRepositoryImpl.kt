package andrespin.data.repository

import andrespin.data.local.LocalKeyDataSource
import andrespin.domain.repository.KeyRepository
import andrespin.domain.usecase.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class KeyRepositoryImpl(private val keyDataSource: LocalKeyDataSource) : KeyRepository {
    override fun getKey(): Flow<String> = keyDataSource.getKey()

    override suspend fun setKey(key: String) = keyDataSource.setKey(key)

}