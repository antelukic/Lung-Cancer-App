package com.lukic.domain.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCase<T> {

    operator fun invoke(): Flow<T>
}

interface SuspendQueryUseCase<T> {

    suspend operator fun invoke(): Flow<T>
}
