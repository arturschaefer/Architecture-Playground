package com.schaefer.architectureplayground.model

data class PagedResult<T>(
    val info: Info,
    val results: List<T>
)