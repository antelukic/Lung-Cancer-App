@file:Suppress("ConstructorParameterNaming")

package com.lukic.data.api.model

import com.lukic.data.parser.ResultParser
import com.lukic.domain.model.ResultModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ApiLungCancerResponse(
    @SerialName("Results")
    val Results: Output
)

@Serializable
data class Output(
    @SerialName("output1")
    val output1: Results
)

@Serializable
data class Results(
    @SerialName("type")
    val type: String,
    @SerialName("value")
    val value: JsonObject
)

fun ApiLungCancerResponse.toResultModel(parser: ResultParser) = ResultModel(
    isCancer = parser.parse(this)
)
