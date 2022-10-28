package com.esaudev.flowworkshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.sharedFlow.collect {
                    Log.d("TEST", "Collector SHARED FLOW: $it")
                }
            }
        }

        lifecycleScope.launch {
            viewModel.flows.collect {
                Log.d("TEST", "Collector Flow: $it" )
            }

            viewModel.stateFlow.collect {
                Log.d("TEST", "Collector StateFlow: $it")
            }
        }

        viewModel.livedata.observe(this) {
            Log.d("TEST", "Observe LiveData: $it")
        }

        viewModel.sendStateFlow()

    }
}