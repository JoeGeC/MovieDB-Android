package com.joe.popular.data

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popular.repository.response.PopularResponse

interface PopularRemote<ListItem, Response : PopularResponse<ListItem>> {
    fun getItems(page: Int): Either<Response?, ErrorResponse?>
}