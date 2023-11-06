package andrespin.domain.sorter

import andrespin.domain.entity.Language
import andrespin.domain.usecase.sorter.GetWordLangUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class GetWordLangUseCaseTest {

    private lateinit var useCase: GetWordLangUseCase
    private lateinit var rusWord: String
    private lateinit var engWord: String
    private lateinit var notIdentifiedWord: String

    @Before
    fun init() {
        useCase = GetWordLangUseCase(mock())
        rusWord = "Собака"
        engWord = "Cat"
        notIdentifiedWord = "Соbаkа"
    }

    @Test
    fun isRusIdentified() = runTest {
        val rusRequest = GetWordLangUseCase.Request(rusWord)
        val rusResp = useCase.process(rusRequest).first()
        val rusRespExpected =  GetWordLangUseCase.Response(Language.Russian)
        assertEquals(rusRespExpected,rusResp)
    }

    @Test
    fun isEngIdentified() = runTest{
        val engRequest = GetWordLangUseCase.Request(engWord)
        val engResp = useCase.process(engRequest).first()
        val engRespExpected =  GetWordLangUseCase.Response(Language.English)
        assertEquals(engRespExpected,engResp)
    }
    @Test
    fun isNotIdentified_Identified() = runTest {
        val notIdentifiedRequest = GetWordLangUseCase.Request(notIdentifiedWord)
        val notIdentifiedResp = useCase.process(notIdentifiedRequest).first()
        val notIdentifiedRespExpected =  GetWordLangUseCase.Response(Language.NotIdentified)
        assertEquals(notIdentifiedRespExpected,notIdentifiedResp)
    }

}