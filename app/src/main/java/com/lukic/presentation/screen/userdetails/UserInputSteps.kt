package com.lukic.presentation.screen.userdetails

enum class UserInputSteps {
    GENDER,
    AGE,
    SMOKING,
    YELLOW_FINGERS,
    ANXIETY,
    PEER_PRESSURE,
    CHRONIC_DISEASE,
    FATIGUE,
    ALLERGY,
    WHEEZING,
    ALCOHOL,
    COUGHING,
    SHORTNESS_OF_BREATH,
    SWALLOWING_DIFFICULTY,
    CHEST_PAIN;

    companion object {
        fun fromInt(value: Int) = values().first { it.ordinal == value }
    }
}
