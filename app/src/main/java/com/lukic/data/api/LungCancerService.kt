package com.lukic.data.api

import com.lukic.data.api.model.ApiLungCancerRequest
import com.lukic.data.api.model.ApiLungCancerResponse

interface LungCancerService {

    suspend fun sendDataToProcess(data: ApiLungCancerRequest): ApiLungCancerResponse?
}
