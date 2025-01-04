package com.joe.popular.repository

interface PaginatedResponse<ListItem> {
    val page: Int?
    val results: List<ListItem>?
    val totalPages: Int
}