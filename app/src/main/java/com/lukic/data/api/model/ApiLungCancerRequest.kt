@file:Suppress("ConstructorParameterNaming")
package com.lukic.data.api.model

import com.lukic.data.userdetails.model.UserInputRequestBuilder
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiLungCancerRequest(
    @SerialName("Inputs")
    val Inputs: Inputs
)

@Serializable
data class Inputs(
    @SerialName("input1")
    val input1: Inputs1
)

@Serializable
data class Inputs1(
    @SerialName("ColumnNames")
    val ColumnNames: List<String>,
    @SerialName("Values")
    val Values: List<List<String>>
)

private val columnNames by lazy {
    listOf(
        "GENDER",
        "AGE",
        "SMOKING",
        "YELLOW_FINGERS",
        "ANXIETY",
        "PEER_PRESSURE",
        "CHRONIC DISEASE",
        "FATIGUE",
        "ALLERGY",
        "WHEEZING",
        "ALCOHOL CONSUMING",
        "COUGHING",
        "SHORTNESS OF BREATH",
        "SWALLOWING DIFFICULTY",
        "CHEST PAIN",
        "LUNG_CANCER"
    )
}

fun UserInputRequestBuilder.toApiLungCancerRequest() = ApiLungCancerRequest(
    Inputs = Inputs(
        input1 = Inputs1(
            ColumnNames = columnNames,
            Values = listOf(
                listOf(
                    if (gender.isNullOrBlank()) "M" else gender,
                    age.toString(),
                    smoking.toString(),
                    yellowFingers.toString(),
                    anxiety.toString(),
                    peerPressure.toString(),
                    chronicDisease.toString(),
                    fatigue.toString(),
                    allergy.toString(),
                    wheezing.toString(),
                    alcohol.toString(),
                    coughing.toString(),
                    shortnessOfBreath.toString(),
                    swallowingDifficulty.toString(),
                    (chestPain ?: 1).toString(),
                    "Value"
                )
            )
        )
    )
)
