package com.lukic.domain.model

import com.lukic.data.userdetails.model.UserInputModels

data class UserInputModel(val step: Int)

fun UserInputModels.toUserInputModel() = UserInputModel(step = stepNumber)
