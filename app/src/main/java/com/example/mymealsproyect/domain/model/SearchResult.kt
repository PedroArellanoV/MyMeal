package com.example.mymeal.domain.model

import com.example.mymeal.data.remote.model.RequestResults
import com.example.mymeal.data.remote.model.RequestSearchResult

data class SearchResult(
    val results: List<RequestResults>,
    val offset: Int,
    val number: Int,
    val totalResult: Int
)

fun RequestSearchResult.toDomain() = SearchResult(results, offset, number, totalResults)

