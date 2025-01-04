package com.joe.popular.repository.response

interface PaginatedResponse<ListItem> {
    val page: Int?
    val results: List<ListItem>?
    val totalPages: Int
}