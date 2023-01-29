package com.lukic.data.parser

import com.lukic.data.api.model.ApiLungCancerResponse

private const val POSITIVE_ANSWER = "YES"

interface ResultParser {
    fun parse(value: ApiLungCancerResponse): Boolean
}

class ResultParserImpl : ResultParser {

    override fun parse(value: ApiLungCancerResponse): Boolean {
        val values = value.Results.output1.value.values.last().toString().split(",")
        val result = values[values.indexOf(values.last()) - 1].replace(oldChar = '"', newChar = ' ').trim()
        return result == POSITIVE_ANSWER
    }
}
