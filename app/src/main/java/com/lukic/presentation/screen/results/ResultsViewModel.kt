package com.lukic.presentation.screen.results

import androidx.lifecycle.ViewModel
import com.lukic.domain.usecase.QueryResultsUseCase
import com.lukic.presentation.screen.results.resources.ResultTranslations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val EMPTY = ""
private const val DEFAULT_ID = 0

data class Content(
    val animationId: Int,
    val title: String,
    val link: String,
    val body: String,
    val isLoading: Boolean
) {
    companion object {
        val default = Content(DEFAULT_ID, EMPTY, EMPTY, EMPTY, true)
    }
}

class ResultsViewModel(
    private val userResult: QueryResultsUseCase,
    private val translations: ResultTranslations
) : ViewModel() {

    private val bgScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val contentState = MutableStateFlow(Content.default)

    init {
        bgScope.launch {
            userResult()
                .collect { resultModel ->
                    with(translations) {
                        val isSick = resultModel.isCancer
                        contentState.value = Content(
                            animationId = animation(isSick),
                            title = title(isSick),
                            link = link(isSick),
                            body = body(isSick),
                            isLoading = false
                        )
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        bgScope.cancel()
    }
}
