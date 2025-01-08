package com.example.mazadytask.category.data.repoImp

import com.example.mazadytask.category.data.dto.CategoriesDto
import com.example.mazadytask.category.data.network.RemoteDataSource
import com.example.mazadytask.core.domain.DataError
import com.example.mazadytask.core.domain.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


/**
 * created by Karim Haggagi on 1/8/25
 */
@RunWith(MockitoJUnitRunner::class)
class CategoryRepoImplTest {

    private lateinit var SUT: CategoryRepoImpl

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        SUT = CategoryRepoImpl(remoteDataSource)
    }

    @Test
    fun getAllCategories_return_success() = runTest {
        success()
        val result = SUT.getAllRepositories()
        assert(result is Result.Success)

    }

    @Test
    fun getAllCategories_serverIsDown_returnRemoteServerError() = runTest {
        fail()
        val result = SUT.getAllRepositories()
        assert(result is Result.Error)
        assertEquals((result as Result.Error).error , DataError.Remote.SERVER)

    }

    private suspend fun success() {
        whenever(remoteDataSource.getAllCategories())
            .thenReturn(Result.Success(CategoriesDto()))
    }

    private suspend fun fail() {
        whenever(remoteDataSource.getAllCategories())
            .thenReturn(Result.Error(DataError.Remote.SERVER))

    }
}