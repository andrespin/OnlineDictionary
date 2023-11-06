package andrespin.domain

import andrespin.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.mockito.Mockito.mock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import andrespin.domain.entity.Result
import andrespin.domain.usecase.UseCaseException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue

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

//    @Test
//    fun testExecuteUserException() {
//        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
//            override fun process(request: Request): Flow<Response> {
//                assertEquals(this@UseCaseTest.request, request)
//                return flow {
//                    throw UseCaseException.UserException(Throwable())
//                }
//            }
//
//        }
//        runTest {
//            val result = useCase.execute(request).first()
//            assertTrue((result as Result.Error).exception is UseCaseException.UserException)
//        }
//    }
//


    /*



    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteUserException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw UseCaseException.UserException(Throwable())
                }
            }

        }
        runBlockingTest {
            val result = useCase.execute(request).first()
            assertTrue((result as Result.Error).exception is UseCaseException.UserException)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecutePostException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw UseCaseException.PostException(Throwable())
                }
            }

        }
        runBlockingTest {
            val result = useCase.execute(request).first()
            assertTrue((result as Result.Error).exception is UseCaseException.PostException)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteUnknownException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw Throwable()
                }
            }

        }
        runBlockingTest {
            val result = useCase.execute(request).first()
            assertTrue((result as Result.Error).exception is UseCaseException.UnknownException)
        }
    }
     */


}