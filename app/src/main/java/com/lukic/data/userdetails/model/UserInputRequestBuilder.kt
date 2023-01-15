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
    val chestPain: Int?,
    val lungCancer: Int?
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
        var chestPain: Int? = null,
        var lungCancer: Int? = null
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
        private fun lungCancer(value: Int) = apply { this.lungCancer = value }
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
            chestPain,
            lungCancer
        )

        @Suppress("ComplexMethod", "MagicNumber")
        fun buildByStep(step: Int, textValue: String = "", numberValue: Int = 0) {
            when (step) {
                1 -> gender(textValue)
                2 -> age(numberValue)
                3 -> smoking(numberValue)
                4 -> yellowFingers(numberValue)
                5 -> anxiety(numberValue)
                6 -> peerPressure(numberValue)
                7 -> chronicDisease(numberValue)
                8 -> fatigue(numberValue)
                9 -> allergy(numberValue)
                10 -> wheezing(numberValue)
                11 -> alcohol(numberValue)
                12 -> coughing(numberValue)
                13 -> shortnessOfBreath(numberValue)
                14 -> swallowingDifficulty(numberValue)
                15 -> chestPain(numberValue)
                16 -> lungCancer(numberValue)
            }
        }
    }
}
