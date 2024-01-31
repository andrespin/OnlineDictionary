package andrespin.local

import andrespin.domain.NoKey
import andrespin.domain.usecase.UseCaseException
import andrespin.local.source.API_KEY
import andrespin.local.source.LocalKeyDataSourceImpl
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

class LocalKeyDataSourceImplTest {

    private val dataStore = mock<DataStore<Preferences>>()
    private val localKey = LocalKeyDataSourceImpl(dataStore)

    @Test
    fun testGetKey() = runTest {
        val preferences = mock<Preferences>()
        val key = "Key"
        whenever(preferences[API_KEY]).thenReturn(key)
        whenever(dataStore.data).thenReturn(flowOf(preferences))
        val result = localKey.getKey().first()
        assertEquals(key, result)
    }

    @Test
    fun testGetNullKey() = runTest {
        val preferences = mock<Preferences>()
        whenever(preferences[API_KEY]).thenReturn(null)
        whenever(dataStore.data).thenReturn(flowOf(preferences))
        try {
            val result = localKey.getKey().first()
        } catch (e: UseCaseException) {
            val expectedException = UseCaseException.NoKeyException(Exception(NoKey))
            assertEquals(expectedException.message, e.message)
        }
    }




}