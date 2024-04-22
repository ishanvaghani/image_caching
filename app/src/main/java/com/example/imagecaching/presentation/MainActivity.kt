package com.example.imagecaching.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagecaching.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ImagesViewModel>()

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeData()
    }

    private fun setupUI() {
        imageAdapter = ImageAdapter(this, listOf())
        binding.imagesRv.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            addItemDecoration(GridItemDecorator(20))
        }
    }

    private fun observeData() {
        viewModel.fetchImages(100)

        lifecycleScope.launch {
            viewModel.imagesFlow.collect {
                when (it) {
                    is UIEvent.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is UIEvent.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTv.text = it.error
                        binding.errorTv.visibility = View.VISIBLE
                    }
                    is UIEvent.Success -> {
                        binding.progressBar.visibility = View.GONE
                        imageAdapter.update(it.data)
                    }
                }
            }
        }
    }
}