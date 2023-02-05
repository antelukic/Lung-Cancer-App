package com.lukic.presentation.screen.intro

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
import androidx.navigation.fragment.findNavController
import com.lukic.lungcancerapp.R
import com.lukic.lungcancerapp.databinding.FragmentIntroBinding
import com.lukic.presentation.ui.compose.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroFragment : Fragment() {

    private var _binding: FragmentIntroBinding? = null
    private val binding by lazy { _binding!! }

    private val introViewModel by viewModel<IntroViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntroBinding.inflate(inflater)
        binding.setContent()
        return binding.root
    }

    private fun FragmentIntroBinding.setContent() = intro.setContent {
        val pageContentState = introViewModel.contentUiState.collectAsState()
        AppTheme {
            IntroScreen(
                pageContent = pageContentState.value.funFacts,
                onStartClick = {
                    findNavController().navigate(R.id.action_introFragment_to_userDetailsStepGenderFragment)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
