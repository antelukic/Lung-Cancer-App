package com.lukic.domain.model

data class ResultModel(val isCancer: Boolean) {
    companion object {
        val default = ResultModel(isCancer = false)
    }
}
