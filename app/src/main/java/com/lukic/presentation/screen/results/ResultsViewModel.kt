package com.lukic.presentation.screen.results

import androidx.lifecycle.ViewModel
import com.lukic.domain.usecase.QueryResultsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ResultsViewModel(
    private val userResult: QueryResultsUseCase
) : ViewModel() {

    private val bgScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        bgScope.launch {

            userResult()
        }
    }

    override fun onCleared() {
        super.onCleared()
        bgScope.cancel()
    }
}
