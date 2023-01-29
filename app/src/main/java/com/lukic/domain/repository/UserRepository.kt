package com.lukic.domain.repository

import com.lukic.domain.model.ResultModel
import com.lukic.domain.model.UserInputModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun userInputModels(): Flow<UserInputModel>

    suspend fun publishStepUserInputModels(step: Int)

    suspend fun publishUserStepInfo(step: Int, textAnswer: String, numberAnswer: Int)

    suspend fun result(): Flow<ResultModel>
}
