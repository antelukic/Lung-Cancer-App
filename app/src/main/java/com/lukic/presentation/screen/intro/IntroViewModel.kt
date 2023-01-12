package com.lukic.presentation.screen.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukic.domain.intro.resources.IntroTranslations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class Content(val funFacts: List<String>)

class IntroViewModel(
    introTranslations: IntroTranslations
) : ViewModel() {

    val contentUiState = MutableStateFlow(Content(emptyList()))

    init {
        viewModelScope.launch {
            contentUiState.emit(Content(introTranslations.funFacts().toList()))
        }
    }
}
