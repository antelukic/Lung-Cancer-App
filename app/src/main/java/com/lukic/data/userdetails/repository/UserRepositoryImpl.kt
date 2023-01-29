package com.lukic.data.userdetails.repository

import com.lukic.data.api.LungCancerService
import com.lukic.data.api.model.toApiLungCancerRequest
import com.lukic.data.api.model.toResultModel
import com.lukic.data.parser.ResultParser
import com.lukic.data.userdetails.model.Gender
import com.lukic.data.userdetails.model.UserInputRequestBuilder
import com.lukic.data.userdetails.model.toUserInputModel
import com.lukic.domain.model.ResultModel
import com.lukic.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest

private const val EMPTY = ""
private const val DEFAULT_NUMBER_ANSWER = 0
private const val STEP_INCREMENT = 1

class UserRepositoryImpl(
    private val service: LungCancerService,
    private val resultParser: ResultParser
) : UserRepository {

    private val userInputModelsStepPublisher = MutableSharedFlow<Int>()
    private var userInput = UserInputRequestBuilder.Builder()

    override suspend fun publishStepUserInputModels(step: Int) =
        userInputModelsStepPublisher.emit(step + STEP_INCREMENT)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun userInputModels() = userInputModelsStepPublisher
        .mapLatest { stepNumber ->
            val userInputModels = com.lukic.data.userdetails.model.userInputModels()
                .firstOrNull { it.stepNumber == stepNumber } ?: Gender()
            userInputModels.toUserInputModel()
        }

    override suspend fun publishUserStepInfo(step: Int, textAnswer: String, numberAnswer: Int) {
        userInput.buildByStep(
            step = step,
            textValue = if (step == Gender.STEP_NUMBER) textAnswer else EMPTY,
            numberValue = if (step != Gender.STEP_NUMBER) numberAnswer else DEFAULT_NUMBER_ANSWER
        )
    }

    override suspend fun result() = flowOf(
        service.sendDataToProcess(userInput.build().toApiLungCancerRequest())
            ?.toResultModel(parser = resultParser) ?: ResultModel.default
    )
}
