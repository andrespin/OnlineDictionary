package andrespin.remote

import andrespin.remote.networking.WordApiService
import andrespin.remote.source.RemoteWordDataSourceImpl
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock

class RemoteWordDataSourceImplTest {


    private val wordApiService = mock<WordApiService>()
    private val dataSource = RemoteWordDataSourceImpl(wordApiService)

    // Log.d("RemoteWordDataSourceImpl", "word $word , lang $lang, key $key ")
    @Test
    fun testGetWord() = runTest {

       // dataSource.
    }


    /*
        @ExperimentalCoroutinesApi
    @Test
    fun testGetPosts() = runBlockingTest {
        val remotePosts = listOf(PostApiModel(1, 1, "title", "body"))
        val expectedPosts = listOf(Post(1, 1, "title", "body"))
        whenever(postService.getPosts()).thenReturn(remotePosts)
        val result = postDataSource.getPosts().first()
        Assert.assertEquals(expectedPosts, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetPost() = runBlockingTest {
        val id = 1L
        val remotePost = PostApiModel(id, 1, "title", "body")
        val expectedPost = Post(id, 1, "title", "body")
        whenever(postService.getPost(id)).thenReturn(remotePost)
        val result = postDataSource.getPost(id).first()
        Assert.assertEquals(expectedPost, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetPostsThrowsError() = runBlockingTest {
        whenever(postService.getPosts()).thenThrow(RuntimeException())
        postDataSource.getPosts().catch {
            Assert.assertTrue(it is UseCaseException.PostException)
        }.collect()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetPostThrowsError() = runBlockingTest {
        val id = 1L
        whenever(postService.getPost(id)).thenThrow(RuntimeException())
        postDataSource.getPost(id).catch {
            Assert.assertTrue(it is UseCaseException.PostException)
        }.collect()
    }
     */


}