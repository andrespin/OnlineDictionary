package andrespin.remote.di

import andrespin.remote.networking.WordApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    fun getWordApi(gson: Gson): WordApiService = Retrofit.Builder()
        .baseUrl("https://dictionary.yandex.net/api/v1/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(WordApiService::class.java)

}
