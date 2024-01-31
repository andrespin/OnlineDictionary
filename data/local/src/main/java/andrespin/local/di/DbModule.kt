package andrespin.local.di

import andrespin.data.local.LocalKeyDataSource
import andrespin.local.db.AppDatabase
import andrespin.local.db.word.WordDao
import andrespin.local.source.LocalKeyDataSourceImpl
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preferences")

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "my-database"
        ).build()

    @Provides
    fun provideWordDao(appDatabase: AppDatabase): WordDao = appDatabase.wordDao()

    @Provides
    fun provideKey(@ApplicationContext context: Context)
            : LocalKeyDataSource = LocalKeyDataSourceImpl(context.dataStore)

}