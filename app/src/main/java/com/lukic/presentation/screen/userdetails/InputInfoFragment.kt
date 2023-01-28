package com.lukic.presentation.screen.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.lukic.lungcancerapp.R
import com.lukic.lungcancerapp.databinding.FragmentInputInfoBinding
import com.lukic.presentation.ui.compose.theme.AppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class InputInfoFragment : Fragment() {

    private var _binding: FragmentInputInfoBinding? = null
    private val binding by lazy { _binding!! }
    private val viewModel by viewModel<InputInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputInfoBinding.inflate(inflater)
        binding.userDetails.setContent {
            val content = viewModel.contentUiState.collectAsState().value
            AppTheme {
                InputInfoScreen(
                    content = content,
                    onGenderClick = { gender -> viewModel.publishTextAnswer(content.step, gender) },
                    onYesClick = { answer -> viewModel.publishNumberAnswer(content.step, answer) },
                    onNoClick = { answer -> viewModel.publishNumberAnswer(content.step, answer) },
                    onAgeSelected = { age -> viewModel.publishNumberAnswer(content.step, age) },
                    onArrowBack = { viewModel.navigateBack(content.step) },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                )
            }
        }

        navigateToResults()

        return binding.root
    }

    private fun navigateToResults() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.navigateToResults.collect {
                        findNavController().navigate(R.id.action_userDetailsStepGenderFragment_to_resultsFragment)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
