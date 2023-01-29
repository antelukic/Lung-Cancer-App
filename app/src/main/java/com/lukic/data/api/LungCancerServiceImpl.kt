package com.lukic.data.api

import com.lukic.data.api.model.ApiLungCancerRequest
import com.lukic.data.api.model.ApiLungCancerResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class LungCancerServiceImpl(private val client: HttpClient) : LungCancerService {

    override suspend fun sendDataToProcess(data: ApiLungCancerRequest): ApiLungCancerResponse? = kotlin.runCatching {
        client.post {
            setBody(data)
        }.body() as ApiLungCancerResponse?
    }.onFailure {
        it.printStackTrace()
    }.getOrNull()
}
