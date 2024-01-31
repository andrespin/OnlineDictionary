package andrespin.domain.local

import andrespin.domain.adjective
import andrespin.domain.adverb
import andrespin.domain.entity.Language
import andrespin.domain.entity.Word
import andrespin.domain.entity.WordDescription
import andrespin.domain.noun
import andrespin.domain.repository.WordRepository
import andrespin.domain.usecase.local.word.GetAllWordsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetAllWordsUseCaseTest {

    private lateinit var useCase: GetAllWordsUseCase

    private lateinit var wordRepo: WordRepository

    private val wordDogDescEnList = listOf(
        WordDescription(noun, "dog", "собака, кобель, дог"),
        WordDescription(adjective, "dog", "собачий"),
        WordDescription(adverb, "dog", "собачьи")
    )

    private val dogWordEn = Word(
        "dog", "dɒg", wordDogDescEnList, true, Language.English
    )

    private val wordDogDescRuList = listOf(
        WordDescription(noun, "собака", "dog, dog, dog"),
        WordDescription(adjective, "собака", "dog"),
        WordDescription(adverb, "собака", "dog")
    )

    private val dogWordRu = Word(
        "собака", "собака", wordDogDescRuList, true, Language.Russian
    )

    @Before
    fun init() {
        wordRepo = mock<WordRepository>()
        useCase = GetAllWordsUseCase(wordRepo)
    }

    @Test
    fun is_Correct() = runTest{
        val allWords = listOf(dogWordEn, dogWordRu)
        whenever(wordRepo.getWords()).thenReturn(flowOf(allWords))
        val resp = useCase.invoke().first()
        Assert.assertEquals(allWords, resp)
    }

}