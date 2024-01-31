package andrespin.domain.sorter

import andrespin.domain.adjective
import andrespin.domain.adverb
import andrespin.domain.entity.Language
import andrespin.domain.entity.Word
import andrespin.domain.entity.WordDescription
import andrespin.domain.noun
import andrespin.domain.usecase.sorter.GetWordLangUseCase
import andrespin.domain.usecase.sorter.SortWordLangUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class SortWordLangUseCaseTest {

    private lateinit var useCase: SortWordLangUseCase

    private lateinit var wordLangUseCase: GetWordLangUseCase

    private val wordDogDescEnList = listOf(
        WordDescription(noun, "dog", "собака, кобель, дог"),
        WordDescription(adjective, "dog", "собачий"),
        WordDescription(adverb, "dog", "собачьи")
    )

    private val dogWordEn = Word("dog", "dɒg", wordDogDescEnList, true, Language.English)

    private val wordDogDescRuList = listOf(
        WordDescription(noun, "собака", "dog, dog, dog"),
        WordDescription(adjective, "собака", "dog"),
        WordDescription(adverb, "собака", "dog")
    )

    private val dogWordRu = Word("собака", "собака", wordDogDescRuList, true, Language.Russian)

    private val dogWordRuList = listOf(dogWordRu)

    private val wordList = listOf(dogWordRu, dogWordEn)

    @Before
    fun init() {
        wordLangUseCase = GetWordLangUseCase()
        useCase = SortWordLangUseCase(wordLangUseCase)

    }

    @Test
    fun is_Correct() = runTest {

        val ruWordsResp = useCase.invoke(wordList, Language.Russian).first()

        val ruWordsExpected = dogWordRuList

        Assert.assertEquals(ruWordsExpected, ruWordsResp)
    }

}
