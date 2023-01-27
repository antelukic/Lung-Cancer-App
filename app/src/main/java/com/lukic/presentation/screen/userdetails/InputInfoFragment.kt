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
import com.lukic.lungcancerapp.databinding.FragmentInputInfoBinding
import com.lukic.presentation.ui.compose.theme.AppTheme
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
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
