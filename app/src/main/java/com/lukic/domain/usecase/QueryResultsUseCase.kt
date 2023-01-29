package com.lukic.domain.usecase

import com.lukic.domain.model.ResultModel
import com.lukic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class QueryResultsUseCase(private val repository: UserRepository) : SuspendQueryUseCase<ResultModel> {

    override suspend operator fun invoke(): Flow<ResultModel> = repository.result()
}
