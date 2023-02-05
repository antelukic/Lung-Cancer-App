package com.lukic.domain.usecase

import com.lukic.domain.repository.UserRepository

class PublishUserStepInfoUseCase(private val userRepository: UserRepository) :
    CommandUseCaseWith3Params<Int, String, Int> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun invoke(step: Int, textAnswer: String, numberAnswer: Int) =
        userRepository.publishUserStepInfo(step, textAnswer, numberAnswer)
}
