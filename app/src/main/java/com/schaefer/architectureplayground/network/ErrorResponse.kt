package com.schaefer.architectureplayground.network

data class ErrorResponse(
    val errorDescription: String,
    val causes: Map<String, String> = emptyMap()
)