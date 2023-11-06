package andrespin.domain.sorter

import andrespin.domain.adjective
import andrespin.domain.adverb
import andrespin.domain.entity.Language
import andrespin.domain.entity.PreviousWord
import andrespin.domain.entity.Word
import andrespin.domain.entity.WordDescription
import andrespin.domain.noun
import andrespin.domain.usecase.sorter.FindMatchingLettersInWordUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class FindMatchingLettersInWordUseCaseTest {

    private lateinit var useCase: FindMatchingLettersInWordUseCase
    private lateinit var wordDogDescEnList: List<WordDescription>
    private lateinit var dogWordEn: Word
    private lateinit var highlightedExpected: List<PreviousWord>
    private lateinit var dogWordEnList: List<Word>

    @Before
    fun init() {

        useCase = FindMatchingLettersInWordUseCase(mock())

        wordDogDescEnList = listOf(
            WordDescription(noun, "dog", "собака, кобель, дог"),
            WordDescription(adjective, "dog", "собачий"),
            WordDescription(adverb, "dog", "собачьи")
        )

        dogWordEnList =
            listOf(Word("dog", "dɒg", wordDogDescEnList, true, Language.English))

        dogWordEn = Word("dog", "dɒg", wordDogDescEnList, true, Language.English)

        highlightedExpected = mutableListOf(
            PreviousWord(Word("dog"), 0, 1),
        )

    }

    @Test
    fun testProcess() = runTest {

        val req = FindMatchingLettersInWordUseCase.Request(dogWordEnList, "D")

        val resp = useCase.process(req).first()

        val highlightedStartStopList = listOf(resp.list[0].start, resp.list[0].end)

        val expectedStartStopList = listOf(highlightedExpected[0].start, highlightedExpected[0].end)

        assertEquals(expectedStartStopList, highlightedStartStopList)

    }


}