package com.lukic.domain.usecase

import com.lukic.domain.model.UserInputModel
import com.lukic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class QueryUserInputStepUseCase(private val userRepository: UserRepository) : QueryUseCase<UserInputModel> {

    override suspend fun invoke(): Flow<UserInputModel> = userRepository.userInputModels()
}
