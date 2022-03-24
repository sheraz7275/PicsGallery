package com.example.picgallery.ui

import android.util.Log
import com.example.picgallery.CoroutineTestRule
import com.example.picgallery.dataSource.FakeDataSource
import com.example.picgallery.dataSource.ImageDataSource
import com.example.picgallery.repository.FakeRepository
import com.example.picgallery.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.isNotNull
import java.util.function.Predicate.isEqual
import kotlin.coroutines.CoroutineContext


@ExperimentalCoroutinesApi
class ImageListViewModelTest{

    private lateinit var dataSource: ImageDataSource
    private lateinit var repository: ImageRepository
    private lateinit var viewModel: ImageListViewModel






    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Test
    fun `image list stateFlow test - success`() = runBlockingTest {

        dataSource=FakeDataSource()
        repository=FakeRepository(dataSource)
        viewModel= ImageListViewModel(repository)

        val data= viewModel.imageList.first()






    }



}