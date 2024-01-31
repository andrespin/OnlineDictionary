package andrespin.domain

//import andrespin.domain.usecase.UseCase
//import andrespin.domain.entity.Result
//
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//import org.mockito.kotlin.mock
//
//
//
//class UseCaseTest {
//
//    @Suppress("DEPRECATION")
//    private val configuration = UseCase.Configuration(TestCoroutineDispatcher())
//    private val request = mock<UseCase.Request>()
//    private val response = mock<UseCase.Response>()
//
//    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>
//
//    @Before
//    fun setUp() {
//        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
//            override fun process(request: Request): Flow<Response> {
//                assertEquals(this@UseCaseTest.request, request)
//                return flowOf(response)
//            }
//
//        }
//    }
//
//    @Test
//    fun testExecuteSuccess() = runBlocking {
//        val result = useCase.execute(request).first()
//        assertEquals(Result.Success(response), result)
//    }
//
//}