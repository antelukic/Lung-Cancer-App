package com.lukic.data.userdetails.repository

import com.lukic.data.userdetails.model.Gender
import com.lukic.data.userdetails.model.UserInputRequestBuilder
import com.lukic.data.userdetails.model.toUserInputModel
import com.lukic.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class UserRepositoryImpl : UserRepository {

    private val userInputModelsStepPublisher = MutableSharedFlow<Int>(replay = 1)
    private var userInput = UserInputRequestBuilder.Builder()

    override suspend fun publishStepUserInputModels(step: Int) = userInputModelsStepPublisher.emit(step + 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun userInputModels() = userInputModelsStepPublisher
        .flatMapLatest { stepNumber ->
            val userInputModels = com.lukic.data.userdetails.model.userInputModels()
                .firstOrNull { it.stepNumber == stepNumber } ?: Gender()
            flowOf(userInputModels.toUserInputModel())
        }

    override suspend fun publishUserStepInfo(step: Int, textAnswer: String, numberAnswer: Int) {
        userInput.buildByStep(
            step = step,
            textValue = if (step != 0) textAnswer else "",
            numberValue = if (step == 0) numberAnswer else 0
        )
    }

    override suspend fun sendUserInfo() {
        userInput.build()
    }
}
