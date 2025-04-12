package com.tommy.core_domain.utils

data class Page<T>(
    val totalPages: Int,
    val currentPage: Int,
    val totalElements: Long,
    val currentElements: Long,
    val content: List<T>
)
