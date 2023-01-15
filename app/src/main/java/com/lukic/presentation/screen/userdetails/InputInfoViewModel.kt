package com.lukic.presentation.screen.userdetails

import androidx.lifecycle.ViewModel
import com.lukic.domain.model.UserInputModel
import com.lukic.domain.usecase.PublishUserInputStepUseCase
import com.lukic.domain.usecase.PublishUserStepInfoUseCase
import com.lukic.domain.usecase.QueryUserInputStepUseCase
import com.lukic.presentation.screen.userdetails.resources.InputInfoTranslations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val DEFAULT_TEXT_ANSWER = ""
private const val DEFAULT_NUMBER_ANSWER = 0
private const val INITIAL_STEP = -1

data class Content(
    val stepNumber: Int,
    val question: String
) {
    companion object {
        val Default = Content(0, "")
    }
}

class InputInfoViewModel(
    private val inputInfoTranslations: InputInfoTranslations,
    private val publishUserInputStep: PublishUserInputStepUseCase,
    private val publishUserStepInfo: PublishUserStepInfoUseCase,
    private val userInputStep: QueryUserInputStepUseCase
) : ViewModel() {

    val contentUiState = MutableStateFlow(Content.Default)
    private val bgScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        bgScope.launch {
            publishUserInputStep.invoke(INITIAL_STEP)

            userInputStep().collect { contentUiState.value = it.toContent() }
        }
    }

    private fun UserInputModel.toContent(): Content =
        Content(
            stepNumber = step,
            question = inputInfoTranslations.inputInfoQuestion(step)
        )

    fun publishTextAnswer(step: Int, answer: String) {
        bgScope.launch {
            publishUserInputStep(step)
            publishUserStepInfo(step = step, textAnswer = answer, numberAnswer = DEFAULT_NUMBER_ANSWER)
        }
    }

    fun publishNumberAnswer(step: Int, answer: Int) {
        bgScope.launch {
            publishUserInputStep(step)
            publishUserStepInfo(step = step, numberAnswer = answer, textAnswer = DEFAULT_TEXT_ANSWER)
        }
    }

    override fun onCleared() {
        super.onCleared()
        bgScope.cancel()
    }
}
