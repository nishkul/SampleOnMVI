package com.example.sampleonmvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sampleonmvi.data.model.FakeUserDataDTO
import com.example.sampleonmvi.databinding.ActivityMainBinding
import com.example.sampleonmvi.ui.main.MainAdapter
import com.example.sampleonmvi.ui.main.intent.MainIntent
import com.example.sampleonmvi.ui.main.viewmodel.MainViewModel
import com.example.sampleonmvi.ui.main.viewstate.MainVIewSate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val mainAdapter = MainAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        observeViewModels()

        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.GetPosts)
        }
    }

    private fun observeViewModels() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainVIewSate.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                    is MainVIewSate.Success<*> -> {
                        binding.progress.visibility = View.GONE
                        mainAdapter.addItems(it.data as List<FakeUserDataDTO?>)
                    }
                    is MainVIewSate.Error -> {
                        binding.progress.visibility = View.GONE
                    }

                }

            }
        }
    }

    private fun initView() {
        binding.rvPosts.adapter = mainAdapter

    }
}