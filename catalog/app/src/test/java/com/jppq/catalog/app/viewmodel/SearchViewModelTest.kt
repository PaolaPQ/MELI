package com.jppq.catalog.app.viewmodel

import com.jppq.catalog.app.api.model.EmptyApiResponse
import com.jppq.catalog.app.api.model.SearchApiModel
import com.jppq.catalog.app.api.model.SuccessApiResponse
import com.jppq.catalog.app.api.service.SearchService
import com.jppq.catalog.app.model.SearchModel
import com.jppq.catalog.app.model.SearchModelImpl
import com.jppq.catalog.app.observeOnce
import com.jppq.catalog.app.utils.ProductMockCreator.Companion.createProductResponse
import com.jppq.catalog.app.utils.SearchMockCreator.Companion.createSearchEmptyResponse
import com.jppq.catalog.app.utils.SearchMockCreator.Companion.createSearchResponse
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SearchViewModelTest : BaseTest<SearchViewModelTest>() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var service: SearchService
    private lateinit var model: SearchModel

    private val textToSearch = "Moto g6"
    private val productToSearch = "123"

    @Before
    override fun setupTest() {
        service = Mockito.mock(SearchService::class.java)
        model = SearchModelImpl(service)
        viewModel = SearchViewModel(model)
    }

    @Test
    fun searchSuccess_Test() = runBlockingTest {
        val response: SearchApiModel = createSearchResponse()
        val productResponse = response.results[0]

        Mockito.`when`(
            service.searchByText(textToSearch)
        ).thenReturn(SuccessApiResponse(response))

        viewModel.searchByText(textToSearch)

        viewModel.searchResponse.observeOnce {
            when (it) {
                is EmptyResponse -> falseAssertion()
                is ErrorResponse -> falseAssertion()
                is SuccessResponse -> {
                    val product = it.body.results[0]
                    Assert.assertEquals(product.id, productResponse.id)
                    Assert.assertEquals(product.title, productResponse.title)
                    Assert.assertEquals(product.price, productResponse.price)
                }
            }
        }
    }

    @Test
    fun searchSuccessEmpty_Test() = runBlockingTest {
        val response: SearchApiModel = createSearchEmptyResponse()

        Mockito.`when`(
            service.searchByText(textToSearch)
        ).thenReturn(SuccessApiResponse(response))

        viewModel.searchByText(textToSearch)

        viewModel.searchResponse.observeOnce {
            when (it) {
                is EmptyResponse -> falseAssertion()
                is ErrorResponse -> falseAssertion()
                is SuccessResponse -> {
                    Assert.assertTrue(it.body.results.size == 0)
                }
            }
        }
    }

    @Test
    fun searchEmptpyResponse_Test() = runBlockingTest {
        Mockito.`when`(
            service.searchByText(textToSearch)
        ).thenReturn(EmptyApiResponse())

        viewModel.searchByText(textToSearch)

        viewModel.searchResponse.observeOnce {
            when (it) {
                is EmptyResponse -> trueAssertion()
                is ErrorResponse -> falseAssertion()
                is SuccessResponse -> falseAssertion()
            }
        }
    }

    @Test
    fun searchErrorResponse_Test() = runBlockingTest {
        Mockito.`when`(
            service.searchByText(textToSearch)
        ).thenReturn(ERROR_RESPONSE)

        viewModel.searchByText(textToSearch)

        viewModel.searchResponse.observeOnce {
            when (it) {
                is EmptyResponse -> falseAssertion()
                is ErrorResponse -> trueAssertion()
                is SuccessResponse -> falseAssertion()
            }
        }
    }


    @Test
    fun productSuccess_Test() = runBlockingTest {
        val response = createProductResponse()

        Mockito.`when`(
            service.getProduct(productToSearch)
        ).thenReturn(SuccessApiResponse(response))

        viewModel.getProduct(productToSearch)

        viewModel.productResponse.observeOnce {
            when (it) {
                is EmptyResponse -> falseAssertion()
                is ErrorResponse -> falseAssertion()
                is SuccessResponse -> {
                    Assert.assertEquals(it.body.title, response.title)
                    Assert.assertEquals(it.body.currentPrice, response.currentPrice)
                }
            }
        }
    }

    @Test
    fun productEmptpyResponse_Test() = runBlockingTest {
        Mockito.`when`(
            service.getProduct(productToSearch)
        ).thenReturn(EmptyApiResponse())

        viewModel.getProduct(productToSearch)

        viewModel.productResponse.observeOnce {
            when (it) {
                is EmptyResponse -> trueAssertion()
                is ErrorResponse -> falseAssertion()
                is SuccessResponse -> falseAssertion()
            }
        }
    }

    @Test
    fun productErrorResponse_Test() = runBlockingTest {
        Mockito.`when`(
            service.getProduct(productToSearch)
        ).thenReturn(ERROR_RESPONSE)

        viewModel.getProduct(productToSearch)

        viewModel.productResponse.observeOnce {
            when (it) {
                is EmptyResponse -> falseAssertion()
                is ErrorResponse -> trueAssertion()
                is SuccessResponse -> falseAssertion()
            }
        }
    }
}