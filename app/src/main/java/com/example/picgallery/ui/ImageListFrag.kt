package com.example.picgallery.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.picgallery.R
import com.example.picgallery.dataSource.remote.RestService
import com.example.picgallery.databinding.FragmentImageListBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@FragmentScoped
class ImageListFrag : Fragment() {

    private val viewModel: ImageListViewModel by viewModels()
    private lateinit var binding: FragmentImageListBinding

    @Inject
    lateinit var recAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentImageListBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this

        observeData()

        searchViewListenerImplementation()

        return binding.root
    }


    private fun observeData() {
        lifecycleScope.launch {
            viewModel.imageList.flowWithLifecycle(lifecycle).collectLatest {
                it?.let {
                    binding.recImages.apply {
                        adapter =
                            recAdapter.withLoadStateFooter(footer = LoadingStateAdapter { recAdapter })
                    }
                    recAdapter.submitData(it)
                }
            }
        }
    }


    private fun searchViewListenerImplementation() {
        binding.editSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.apiCall(it)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}