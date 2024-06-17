package andrespin.local.di

import andrespin.data.local.LocalKeyDataSource
import andrespin.data.local.LocalWordDataSource
import andrespin.local.source.LocalKeyDataSourceImpl
import andrespin.local.source.LocalWordDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindWordDataSource(wordDataSource: LocalWordDataSourceImpl): LocalWordDataSource

}