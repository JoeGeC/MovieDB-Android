package com.joe.popular.data

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popular.repository.PaginatedResponse

interface PaginatedRemote<ListItem, Response : PaginatedResponse<ListItem>> {
    fun getItems(page: Int): Either<Response?, ErrorResponse?>
}