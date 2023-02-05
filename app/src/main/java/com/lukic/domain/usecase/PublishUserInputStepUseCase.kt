package com.lukic.domain.usecase

import com.lukic.domain.repository.UserRepository

class PublishUserInputStepUseCase(private val userRepository: UserRepository) : CommandUseCaseWithParam<Int> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun invoke(step: Int) = userRepository.publishStepUserInputModels(step)
}
