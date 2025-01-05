package com.joe.popular.repository.response

interface PopularResponse<ListItem> {
    val page: Int?
    val results: List<ListItem>?
    val totalPages: Int
}