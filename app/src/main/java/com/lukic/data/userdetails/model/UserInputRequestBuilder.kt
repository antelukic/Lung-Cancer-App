package com.lukic.data.userdetails.model

@Suppress("LongParameterList")
class UserInputRequestBuilder private constructor(
    val gender: String?,
    val age: Int?,
    val smoking: Int?,
    val yellowFingers: Int?,
    val anxiety: Int?,
    val peerPressure: Int?,
    val chronicDisease: Int?,
    val fatigue: Int?,
    val allergy: Int?,
    val wheezing: Int?,
    val alcohol: Int?,
    val coughing: Int?,
    val shortnessOfBreath: Int?,
    val swallowingDifficulty: Int?,
    val chestPain: Int?
) {

    @Suppress("TooManyFunctions")
    data class Builder(
        var gender: String? = null,
        var age: Int? = null,
        var smoking: Int? = null,
        var yellowFingers: Int? = null,
        var anxiety: Int? = null,
        var peerPressure: Int? = null,
        var chronicDisease: Int? = null,
        var fatigue: Int? = null,
        var allergy: Int? = null,
        var wheezing: Int? = null,
        var alcohol: Int? = null,
        var coughing: Int? = null,
        var shortnessOfBreath: Int? = null,
        var swallowingDifficulty: Int? = null,
        var chestPain: Int? = null
    ) {
        private fun gender(value: String) = apply { this.gender = value }
        private fun age(value: Int) = apply { this.age = value }
        private fun smoking(value: Int) = apply { this.smoking = value }
        private fun yellowFingers(value: Int) = apply { this.yellowFingers = value }
        private fun anxiety(value: Int) = apply { this.anxiety = value }
        private fun peerPressure(value: Int) = apply { this.peerPressure = value }
        private fun chronicDisease(value: Int) = apply { this.chronicDisease = value }
        private fun fatigue(value: Int) = apply { this.fatigue = value }
        private fun allergy(value: Int) = apply { this.allergy = value }
        private fun wheezing(value: Int) = apply { this.wheezing = value }
        private fun alcohol(value: Int) = apply { this.alcohol = value }
        private fun coughing(value: Int) = apply { this.coughing = value }
        private fun shortnessOfBreath(value: Int) = apply { this.shortnessOfBreath = value }
        private fun swallowingDifficulty(value: Int) = apply { this.swallowingDifficulty = value }
        private fun chestPain(value: Int) = apply { this.chestPain = value }
        fun build() = UserInputRequestBuilder(
            gender,
            age,
            smoking,
            yellowFingers,
            anxiety,
            peerPressure,
            chronicDisease,
            fatigue,
            allergy,
            wheezing,
            alcohol,
            coughing,
            shortnessOfBreath,
            swallowingDifficulty,
            chestPain
        )

        @Suppress("ComplexMethod")
        fun buildByStep(step: Int, textValue: String = "", numberValue: Int = 0) {
            when (step) {
                Gender.STEP_NUMBER -> gender(textValue)
                Age.STEP_NUMBER -> age(numberValue)
                Smoking.STEP_NUMBER -> smoking(numberValue)
                YellowFingers.STEP_NUMBER -> yellowFingers(numberValue)
                Anxiety.STEP_NUMBER -> anxiety(numberValue)
                PeerPressure.STEP_NUMBER -> peerPressure(numberValue)
                ChronicDisease.STEP_NUMBER -> chronicDisease(numberValue)
                Fatigue.STEP_NUMBER -> fatigue(numberValue)
                Allergy.STEP_NUMBER -> allergy(numberValue)
                Wheezing.STEP_NUMBER -> wheezing(numberValue)
                Alcohol.STEP_NUMBER -> alcohol(numberValue)
                Coughing.STEP_NUMBER -> coughing(numberValue)
                ShortnessOfBreath.STEP_NUMBER -> shortnessOfBreath(numberValue)
                SwallowingDifficulty.STEP_NUMBER -> swallowingDifficulty(numberValue)
                ChestPain.STEP_NUMBER -> chestPain(numberValue)
            }
        }
    }
}
