package com.lukic.presentation.screen.results

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import com.lukic.lungcancerapp.R
import com.lukic.lungcancerapp.databinding.FragmentResultsBinding
import com.lukic.presentation.ui.compose.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding by lazy { _binding!! }

    private val viewModel by viewModel<ResultsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater)
        binding.resultsContent.setContent {
            Screen()
        }
        return binding.root
    }

    @Composable
    private fun Screen() {
        AppTheme {
            val contentState = viewModel.contentState.collectAsState()
            ResultScreen(
                contentState = contentState.value,
                onLearnMoreClick = ::openLearnMore,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }

    private fun openLearnMore(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                requireContext(),
                resources.getString(R.string.results_browser_toast_message),
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
