package andrespin.onlinedictionary.di

import andrespin.data.local.LocalKeyDataSource
import andrespin.data.local.LocalWordDataSource
import andrespin.data.remote.RemoteWordDataSource
import andrespin.data.repository.KeyRepositoryImpl
import andrespin.data.repository.WordRepositoryImpl
import andrespin.domain.repository.KeyRepository
import andrespin.domain.repository.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun getWordRepository(
        local: LocalWordDataSource,
        remote: RemoteWordDataSource,
    ): WordRepository = WordRepositoryImpl(
        local,
        remote
    )

    @Provides
    fun getKeyRepository(
        keyDataSource: LocalKeyDataSource,
    ): KeyRepository =
        KeyRepositoryImpl(keyDataSource)

}