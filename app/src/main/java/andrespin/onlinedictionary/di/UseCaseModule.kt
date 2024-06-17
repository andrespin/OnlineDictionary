package andrespin.onlinedictionary.di

import andrespin.domain.repository.KeyRepository
import andrespin.domain.repository.WordRepository
import andrespin.domain.usecase.local.key.CheckKeyUseCase
import andrespin.domain.usecase.local.key.GetKeyUseCase
import andrespin.domain.usecase.local.word.GetAllWordsUseCase
import andrespin.domain.usecase.local.word.GetPrevWordUseCase
import andrespin.domain.usecase.remote.SearchNewWordUseCase
import andrespin.domain.usecase.sorter.FindMatchingLettersInWordUseCase
import andrespin.domain.usecase.sorter.GetWordLangUseCase
import andrespin.domain.usecase.sorter.SortPrevWordsUseCase
import andrespin.domain.usecase.sorter.SortWordLangUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun getFindMatchingLettersInWordUseCase() =
        FindMatchingLettersInWordUseCase()

    @Provides
    fun getGetWordLangUseCase() =
        GetWordLangUseCase()

    @Provides
    fun getKeyUseCase(keyRepository: KeyRepository) = GetKeyUseCase(keyRepository)

    @Provides
    fun getSortWordLangUseCase(
        getWordLangUseCase: GetWordLangUseCase,
    ) = SortWordLangUseCase(
        getWordLangUseCase
    )

    @Provides
    fun getSearchNewWordUseCase(
        wordRepo: WordRepository,
        getWordLang: GetWordLangUseCase,
        getKeyUseCase: GetKeyUseCase,
        getAllWordsUseCase: GetAllWordsUseCase
    ) = SearchNewWordUseCase(
        wordRepo,
        getWordLang,
        getKeyUseCase,
        getAllWordsUseCase
    )

    @Provides
    fun provideGetAllWordsUseCase(wordRepo: WordRepository) = GetAllWordsUseCase(wordRepo)

    @Provides
    fun provideSortPrevWordsUseCase(
        matchingLetters: FindMatchingLettersInWordUseCase,
        langSorter: SortWordLangUseCase,
        getWordLang: GetWordLangUseCase,
    ) = SortPrevWordsUseCase(matchingLetters, langSorter, getWordLang)


    @Provides
    fun provideCheckKeyUseCase(
        keyRepo: KeyRepository,
        wordRepo: WordRepository
    ) = CheckKeyUseCase(keyRepo, wordRepo)

    @Provides
    fun provideGetPrevWordUseCase(
        wordRepo: WordRepository
    ) = GetPrevWordUseCase(wordRepo)

}