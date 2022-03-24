package com.example.picgallery.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.picgallery.dataModel.Photo
import com.example.picgallery.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(private val defaultRepository: ImageRepository) :
    ViewModel() {

    private val _imageList = MutableStateFlow<PagingData<Photo>?>(null)
    val imageList = _imageList.asStateFlow()

    init {
        apiCall("Nature")
    }

    fun apiCall(query: String) {
        viewModelScope.launch {
            defaultRepository.getImageList(query = query).collect {
                _imageList.value = it

            }
        }
    }
}