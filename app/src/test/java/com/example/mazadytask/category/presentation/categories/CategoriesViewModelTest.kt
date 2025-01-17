package com.example.mazadytask.category.presentation.categories

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.example.mazadytask.FakeStringProvider
import com.example.mazadytask.MainDispatcherRule
import com.example.mazadytask.category.data.dto.CategoriesDto
import com.example.mazadytask.category.domain.model.CategoryModel
import com.example.mazadytask.category.domain.model.ChildrenModel
import com.example.mazadytask.category.domain.repository.CategoriesRepository
import com.example.mazadytask.core.domain.DataError
import com.example.mazadytask.core.domain.Result
import com.example.mazadytask.core.domain.toUiText
import com.example.mazadytask.core.presentation.AndroidStringProvider
import com.example.mazadytask.core.presentation.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


/**
 * created by Karim Haggagi on 1/8/25
 */
@RunWith(MockitoJUnitRunner::class)
class CategoriesViewModelTest {

    private lateinit var SUT: CategoriesViewModel

    @Mock
    private lateinit var categoriesRepository: CategoriesRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        SUT = CategoriesViewModel(categoriesRepository, FakeStringProvider())
    }

    @Test
    fun getAllCategories_return_success() = runTest {
        successGetAllCategories()
        SUT.categoriesFlow.test {
            val result = awaitItem()
            assertEquals(result, mockCategoryList)
        }
    }

    @Test
    fun getAllCategories_serverIsDown_returnRemoteServerError() = runTest {
        failGetAllCategories()
        // Launch categoriesFlow to trigger the initial load
        launch {
            SUT.categoriesFlow.test {
                awaitItem() // This will fail due to error

            }
        }
        SUT.errorState.test {
            val result = awaitItem()
            assert(result is UiText.StringResourceId)
        }
    }

    @Test
    fun selectMainCategory_returnSubCategoryList() = runTest {
        successGetAllCategories()
        // Collect both flows in parallel to ensure we capture all emissions
        runBlocking {
            SUT.categoriesFlow.test {
                awaitItem()
                SUT.setSelectedCategory(0)
            }
        }

        // Test the subcategory flow
        SUT.subCategoryFlow.test {
            val result = awaitItem() // Updated value after selection
            assertEquals(mockCategoryList[0].children, result)
        }
    }

    private suspend fun successGetAllCategories() {
        whenever(categoriesRepository.getAllRepositories())
            .thenReturn(
                Result.Success(
                    mockCategoryList
                )
            )
    }

    private suspend fun failGetAllCategories() {
        whenever(categoriesRepository.getAllRepositories())
            .thenReturn(Result.Error(DataError.Remote.SERVER))
    }

    val mockCategoryList = listOf(
        CategoryModel(
            children = listOf(
                ChildrenModel(
                    id = 1,
                    name = "Child 1",
                    slug = "child-1",
                    circleIcon = "",
                    image = "",
                    disableShipping = 1
                ),
                ChildrenModel(
                    id = 2,
                    name = "Child 2",
                    slug = "child-2",
                    circleIcon = "",
                    image = "",
                    disableShipping = 1
                )
            ),
            circleIcon = "circle_icon_url_1",
            id = 101,
            image = "image_url_1",
            name = "Category 1",
            slug = "category-1"
        ),
        CategoryModel(
            children = listOf(
                ChildrenModel(
                    id = 3,
                    name = "Child 3",
                    slug = "child-3",
                    circleIcon = "",
                    image = "",
                    disableShipping = 1
                ),
                ChildrenModel(
                    id = 4,
                    name = "Child 4",
                    slug = "child-4",
                    circleIcon = "",
                    image = "",
                    disableShipping = 1
                )
            ),
            circleIcon = "circle_icon_url_2",
            id = 102,
            image = "image_url_2",
            name = "Category 2",
            slug = "category-2"
        )
    )

}