package com.lukic.domain.usecase

interface CommandUseCase<T> {

    suspend operator fun invoke(param: T)
}

interface CommandUseCaseWith3Params<S, T, N> {

    suspend operator fun invoke(param1: S, param2: T, param3: N)
}
