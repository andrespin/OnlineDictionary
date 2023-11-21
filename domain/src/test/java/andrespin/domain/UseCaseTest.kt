package andrespin.domain

import andrespin.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.mockito.Mockito.mock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import andrespin.domain.entity.Result
import kotlinx.coroutines.test.runTest

class UseCaseTest {

    private val configuration = UseCase.Configuration(StandardTestDispatcher())
    private val request = mock<UseCase.Request>()
    private val response = mock<UseCase.Response>()

    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>

    @Before
    fun setUp() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flowOf(response)
            }
        }
    }

    @Test
    fun testExecuteSuccess() = runTest {
        val result = useCase.execute(request).first()
        assertEquals(Result.Success(response), result)
    }

}