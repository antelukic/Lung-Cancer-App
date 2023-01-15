package com.lukic.domain.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCase<T> {

    suspend operator fun invoke(): Flow<T>
}
