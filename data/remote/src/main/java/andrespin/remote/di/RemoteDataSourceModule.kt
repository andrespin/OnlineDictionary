package andrespin.remote.di

import andrespin.data.remote.RemoteWordDataSource
import andrespin.remote.source.RemoteWordDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindWordDataSource(wordDataSource: RemoteWordDataSourceImpl): RemoteWordDataSource

}
