package com.exchange.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exchange.model.SearchPics
import com.exchange.repository.DashboardRepo
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {

    @Mock
    private lateinit var repo: DashboardRepo

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var currencyExchange: SearchPics

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var CUT: DashboardViewModel

    private val flower = "yellow flowers"

    private val errorResponse = "error"
    private val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
    private val mockResponse: Response<String> = Response.error<String>(400, errorResponseBody)

    @Before
    fun setUp() {
        CUT = DashboardViewModel(repo, application)
    }

    @Test
    fun `getExchangeRate success`() {
       runBlocking {
           `when`(repo.fetchPics(flower)).thenReturn(Response.success(currencyExchange))
           CUT.getSearchPics(flower)
           Truth.assertThat(repo.fetchPics(flower).code()).isEqualTo(200)
       }
    }

    @Test
    fun `getExchangeRate fail`() {
       runBlocking {
           doReturn(mockResponse).`when`(repo).fetchPics(flower)
           CUT.getSearchPics(flower)
           Truth.assertThat(repo.fetchPics(flower).code()).isEqualTo(400)
       }
    }



}