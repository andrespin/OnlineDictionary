package andrespin.domain.sorter

import andrespin.domain.entity.Language
import andrespin.domain.usecase.sorter.GetWordLangUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetWordLangUseCaseTest {

    private lateinit var useCase: GetWordLangUseCase
    private lateinit var rusWord: String
    private lateinit var engWord: String
    private lateinit var notIdentifiedWord: String

    @Before
    fun init() {
        useCase = GetWordLangUseCase()
        rusWord = "Собака"
        engWord = "Cat"
        notIdentifiedWord = "Соbаkа"
    }

    @Test
    fun isRusIdentified() = runTest {
        val rusResp = useCase.invoke(rusWord).first()
        val rusRespExpected = Language.Russian
        assertEquals(rusRespExpected, rusResp)
    }

    @Test
    fun isEngIdentified() = runTest {
        val engResp = useCase.invoke(engWord).first()
        val engRespExpected = Language.English
        assertEquals(engRespExpected, engResp)
    }

    @Test
    fun isNotIdentified_Identified() = runTest {
        val notIdentifiedResp = useCase.invoke(notIdentifiedWord).first()
        val notIdentifiedRespExpected = Language.NotIdentified
        assertEquals(notIdentifiedRespExpected, notIdentifiedResp)
    }
}
