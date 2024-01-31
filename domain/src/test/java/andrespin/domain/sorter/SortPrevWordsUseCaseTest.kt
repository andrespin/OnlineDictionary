package andrespin.domain.sorter

import andrespin.domain.adjective
import andrespin.domain.adverb
import andrespin.domain.entity.Language
import andrespin.domain.entity.PreviousWord
import andrespin.domain.entity.Word
import andrespin.domain.entity.WordDescription
import andrespin.domain.noun
import andrespin.domain.usecase.sorter.FindMatchingLettersInWordUseCase
import andrespin.domain.usecase.sorter.GetWordLangUseCase
import andrespin.domain.usecase.sorter.SortPrevWordsUseCase
import andrespin.domain.usecase.sorter.SortWordLangUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SortPrevWordsUseCaseTest {

    private lateinit var matchingLetters: FindMatchingLettersInWordUseCase

    private lateinit var langSorter: SortWordLangUseCase

    private lateinit var getWordLang: GetWordLangUseCase

    private lateinit var useCase: SortPrevWordsUseCase

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

    private val highlightedExpected = mutableListOf(
        PreviousWord(Word("Dog"), 0, 1)
    )

    @Before
    fun init() {
        matchingLetters = FindMatchingLettersInWordUseCase()
        getWordLang = GetWordLangUseCase()
        langSorter = SortWordLangUseCase(getWordLang)
        useCase = SortPrevWordsUseCase(matchingLetters, langSorter, getWordLang)
    }

    @Test
    fun is_Correct() = runTest {
        val words = listOf(dogWordEn, dogWordRu)
        val highlighted = useCase.invoke("D", words).first()
        val expectedStartStopList = listOf(highlightedExpected[0].start, highlightedExpected[0].end)
        val highlightedStartStopList = listOf(highlighted[0].start, highlighted[0].end)
        assertEquals(expectedStartStopList, highlightedStartStopList)
    }

}