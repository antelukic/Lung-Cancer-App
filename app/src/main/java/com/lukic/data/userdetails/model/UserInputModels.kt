package com.lukic.data.userdetails.model

import com.lukic.domain.model.UserInputModel

const val YES_ANSWER_VALUE = 2
const val NO_ANSWER_VALUE = 1
const val MALE_ANSWER_VALUE = "M"
const val FEMALE_ANSWER_VALUE = "F"

interface UserInputModels {
    val stepNumber: Int
}

interface YesNoInput {
    val yes: Int
    val no: Int
}

fun userInputModels() = listOf(
    Gender(),
    Age(value = 0),
    Smoking(),
    YellowFingers(),
    Anxiety(),
    PeerPressure(),
    ChronicDisease(),
    Fatigue(),
    Allergy(),
    Wheezing(),
    Alcohol(),
    Coughing(),
    ShortnessOfBreath(),
    SwallowingDifficulty(),
    ChestPain(),
    Results()
)

data class Gender(
    override val stepNumber: Int = STEP_NUMBER,
    val male: String = MALE_ANSWER_VALUE,
    val female: String = FEMALE_ANSWER_VALUE
) : UserInputModels {

    companion object {
        const val STEP_NUMBER = 0
    }
}

data class Age(
    override val stepNumber: Int = STEP_NUMBER,
    val value: Int
) : UserInputModels {

    companion object {
        const val STEP_NUMBER = 1
    }
}

data class Smoking(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 2
    }
}

data class YellowFingers(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 3
    }
}

data class Anxiety(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 4
    }
}

data class PeerPressure(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 5
    }
}

data class ChronicDisease(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 6
    }
}

data class Fatigue(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 7
    }
}

data class Allergy(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 8
    }
}

data class Wheezing(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 9
    }
}

data class Alcohol(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 10
    }
}

data class Coughing(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 11
    }
}

data class ShortnessOfBreath(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 12
    }
}

data class SwallowingDifficulty(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 13
    }
}

data class ChestPain(
    override val stepNumber: Int = STEP_NUMBER,
    override val yes: Int = YES_ANSWER_VALUE,
    override val no: Int = NO_ANSWER_VALUE
) : UserInputModels, YesNoInput {

    companion object {
        const val STEP_NUMBER = 14
    }
}

data class Results(
    override val stepNumber: Int = STEP_NUMBER
) : UserInputModels {

    companion object {
        const val STEP_NUMBER = 15
    }
}

fun UserInputModels.toUserInputModel() = UserInputModel(
    step = stepNumber,
    isLastInput = stepNumber == Results.STEP_NUMBER
)
